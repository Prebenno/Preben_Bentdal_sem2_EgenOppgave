package inf101.game.controller;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.game.view.Roomview;
import inf101.model.Bullet;
import inf101.model.Sprite.CoordinateSprite;

import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController implements KeyListener,ActionListener {

    public IGameController controller;
    public Roomview view;
    public boolean foot;
    public int x_velocity;
    public int y_velocity;
    public List<Integer> mylist = new ArrayList<Integer>();

    public int BULLETDIRX = 1;
    public int BULLETDIRY = 0;

    public int BULLETSPEED = 2;

    public boolean friction = false;
    ScheduledExecutorService movement = Executors.newScheduledThreadPool(1);
    ScheduledExecutorService bullet = Executors.newScheduledThreadPool(2);
    
    private final Set<Integer> pressedKeys = new HashSet<>();
    public boolean once = true;

    public boolean shot = false;
   
    Runnable gametick = new Runnable(){
        public void run(){
            if (x_velocity > 7){
                x_velocity = 7;
            }
            if (y_velocity > 7){
                y_velocity = 7;
            }
            if (friction == false){
                x_velocity = (int) (x_velocity /1.2);
                y_velocity = (int) (y_velocity /1.2);
                
            }
            if (controller.getEnemy() != null){ 
                controller.monsterStep();}
            if (controller.moveObject(y_velocity, x_velocity, controller.getPlayer()) == null){
                x_velocity = 0;
                y_velocity = 0;
            }
            view.repaint();
        }
    };


    
    Runnable bullets = new Runnable(){
        public void run() throws RuntimeException{
            List<Bullet> newBullets = new ArrayList<Bullet>();
            try {
                if (shot ==  true){
                    controller.loadBullet(true,BULLETSPEED * BULLETDIRY,BULLETSPEED * BULLETDIRX);
                    shot = false;
                } 

                if (controller.bulletInChaimber()){
                    for (Bullet bullet : controller.getAllBullets()) {  
                        Bullet shotBullet = controller.moveObject(bullet.getXspeed(), bullet.getYspeed(), bullet);
                        if (shotBullet != null){
                            newBullets.add(shotBullet);
                        
                            }
                        if(controller.checkAndDamage(bullet.getShape(),controller.getEnemy())){
                            newBullets.remove(bullet);
                            }
                        }
                    }
                controller.changeBullets(newBullets); // changing bullets

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
        CoordinateSprite player = controller.getPlayer();
        if (once){
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
                    case KeyEvent.VK_K:
                        controller.resetBullet();
                        break;
                }
            }
        }
        if (controller.getPlayer() != null){;}

        if (movementx == -1) {
            if (x_velocity > 0){
                friction = false;
            }
            controller.changeWalkingDirection(PlayerDirection.LEFT,player);
            BULLETDIRX = -1; 
            BULLETDIRY = 0;
        }
        
        else if (movementx == 1) {
            if (x_velocity < 0){
                friction = false;
            }
            BULLETDIRX = 1; 
            BULLETDIRY = 0;
            controller.changeWalkingDirection(PlayerDirection.RIGHT,player);         
            
        }
        if (movementy == 1) {
            if (y_velocity < 0){
                friction = false;
            }
            BULLETDIRY = 1;
            BULLETDIRX = 0; 
            controller.changeWalkingDirection(PlayerDirection.DOWN,player);
            
        }
        else if (movementy == -1)  {
            if (y_velocity > 0){
                friction = false;
            }
            BULLETDIRY = -1;
            BULLETDIRX = 0; 
            controller.changeWalkingDirection(PlayerDirection.UP,player);      
        }
        if (controller.getPlayer() != null){
            x_velocity += movementx;
            y_velocity += movementy;
        
            FootType x = controller.getWalkingType(player);
            controller.changeFootType(x.next(),player);
        }

         
    }


    
    
    @Override
    public void keyReleased(KeyEvent event) {
        CoordinateSprite player = controller.getPlayer();
        pressedKeys.remove(event.getKeyCode());
        friction = false;
        
        view.repaint();


        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        //nothing
        
    }
    
}
