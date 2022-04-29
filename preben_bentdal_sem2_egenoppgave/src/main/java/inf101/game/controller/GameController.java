package inf101.game.controller;

import inf101.game.States.FootType;
import inf101.game.States.GameState;
import inf101.game.States.Direction;
import inf101.game.view.Roomview;
import inf101.grid.Coordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController implements KeyListener,ActionListener {

    private IGameController controller;
    public Roomview view;
    private int x_velocity;
    private int y_velocity;
    private int BULLETDIRX = 1;
    private int BULLETDIRY = 0;
    private final int BULLETSPEED = 2;
    private final int MAX_SPEED = 7;
    
    private boolean activatedPowerups = false;
    private boolean friction = false;

    ScheduledExecutorService movement;
    ScheduledExecutorService bullet;
    
    private long timeOfLastProjectile;
    private final Set<Integer> pressedKeys = new HashSet<>();
    private boolean once = true;
    private boolean shot = false;
    
    
    Runnable gametick = new Runnable(){
        public void run(){
        if (x_velocity > MAX_SPEED){
            x_velocity = MAX_SPEED;
        }
        if (y_velocity > MAX_SPEED){
            y_velocity = MAX_SPEED;
        }
        if (friction == false){
            x_velocity = (int) (x_velocity /1.2);
            y_velocity = (int) (y_velocity /1.2);
        }
        if (controller.getEnemySprites() != null){ 
            controller.changeEnemies(controller.monsterStep());
        
        }
        if (controller.moveObject(y_velocity, x_velocity, controller.getPlayerSprite()) == null){
            x_velocity = 0;
            y_velocity = 0;
        }
        if(controller.simpleCollision(controller.getPlayerSprite(), controller.getTrapDoor())){
            controller.nextFloor();
        }
        view.repaint();
        controller.powerUpChecker();
            }
        };


    
    Runnable bullets = new Runnable(){
        public void run() throws RuntimeException{
                if (!controller.enemyExists()){ // if enemies dont exsist
                    controller.activateTrapDoor();
                    if (!activatedPowerups){
                        controller.activatePowerUps();
                        activatedPowerups = true;
                    }
                }
                if (controller.getPlayerSprite() == null){
                    bullet.shutdown();
                    movement.shutdown();
                    controller.changeGameState(GameState.GAME_OVER);
                }
                
                try {  // to get the error messages from the thread
                    controller.moveAllBullets();
                    controller.checkAndDamageBullets();
                        
                } catch (Exception e){  
                    Thread t = Thread.currentThread();
                    t.getUncaughtExceptionHandler().uncaughtException(t, e);
                }
            }
        

    };

    public GameController(IGameController controller, Roomview view){
        this.controller = controller;
        this.view = view;
        this.view.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //nothing
        
    }

    // code in keypressed for handling multiple keypresses at the same time gotten from
    //https://stackoverflow.com/questions/2623995/swings-keylistener-and-multiple-keys-pressed-at-the-same-time
    @Override
    public void keyPressed(KeyEvent event) {
        friction = true;
        
        CoordinateSprite player = controller.getPlayerSprite();
        if (once){
            this.movement = Executors.newScheduledThreadPool(1);
            this.bullet = Executors.newScheduledThreadPool(2);
            movement.scheduleAtFixedRate(gametick, 0, 30, TimeUnit.MILLISECONDS);
            bullet.scheduleAtFixedRate(bullets, 0, 6, TimeUnit.MILLISECONDS);
            once = false;
        }
        pressedKeys.add(event.getKeyCode());
        int movementx = 0;
        int movementy = 0;
        
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case KeyEvent.VK_UP:
                        movementy = -1;
                        break;
                    case KeyEvent.VK_LEFT:
                        movementx = -1;
                        break;
                    case KeyEvent.VK_DOWN:
                        movementy = 1;
                        break;
                    case KeyEvent.VK_RIGHT:
                        movementx = 1;
                        break;
                    case KeyEvent.VK_SPACE:
                        shot = true;
                        break;
                    case KeyEvent.VK_P:
                        if (controller.getGameState().equals(GameState.ACTIVE_GAME)){
                            bullet.shutdown();
                            movement.shutdown();
                            controller.changeGameState(GameState.PAUSED_GAME);
                        }else {
                            this.movement = Executors.newScheduledThreadPool(1);
                            this.bullet = Executors.newScheduledThreadPool(2);
                            movement.scheduleAtFixedRate(gametick, 0, 30, TimeUnit.MILLISECONDS);
                            bullet.scheduleAtFixedRate(bullets, 0, 6, TimeUnit.MILLISECONDS);
                            controller.changeGameState(GameState.ACTIVE_GAME);

                        }
                        break;
                }
            }
        }
        if (movementx == -1) {
            if (x_velocity > 0){
                friction = false;
            }
            controller.changeWalkingDirection(Direction.LEFT,player);
        }
        else if (movementx == 1) {
            if (x_velocity < 0){
                friction = false;
            }
            controller.changeWalkingDirection(Direction.RIGHT,player);          
        }
        if (movementy == 1) {
            if (y_velocity < 0){
                friction = false;
            }
            controller.changeWalkingDirection(Direction.DOWN,player); 
        }
        else if (movementy == -1)  {
            if (y_velocity > 0){
                friction = false;
            }
            controller.changeWalkingDirection(Direction.UP,player);      
        }
        if (controller.getPlayerSprite() != null){
            x_velocity += movementx;
            y_velocity += movementy;
        
            if (shot == false){
                FootType x = controller.getWalkingType(player);
                controller.changeFootType(x.next(),player);
            }
            else{
                long timeNow = System.currentTimeMillis();
                long time = timeNow - timeOfLastProjectile;
                if (time > (controller.getTimeBetweenShots()) || time <0){
                    Coordinate newCord = controller.directiontoCoordinate(controller.getPlayerSprite());
                    controller.loadBullet(true,BULLETSPEED * newCord.getRow(),BULLETSPEED * newCord.getColumn(),controller.getPlayerSprite().getCoordinate());
                    timeOfLastProjectile = timeNow;
                    }
                shot = false;
            }
            
        }
        

         
    }


    
    
    @Override
    public void keyReleased(KeyEvent event) {
        pressedKeys.remove(event.getKeyCode());
        friction = false;
        
        view.repaint();


        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        //nothing
        
    }

}   