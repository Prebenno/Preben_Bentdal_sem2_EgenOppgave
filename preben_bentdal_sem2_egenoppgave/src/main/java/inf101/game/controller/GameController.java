package inf101.game.controller;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.game.view.Roomview;
import inf101.model.Sprite.CoordinateSprite;

import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
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

    public int bulletdirX = 0;
    public int bulletdirY = 0;

    public boolean friction = false;
    ScheduledExecutorService movement = Executors.newScheduledThreadPool(1);
    
    private final Set<Integer> pressedKeys = new HashSet<>();
    public boolean once = true;
   

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
                //System.out.println("x_Velocity = " + x_velocity);
                //System.out.println("y_Velocity = " + y_velocity);
            } 
            if (!controller.moveObject(y_velocity, x_velocity, controller.getPlayer())){
                x_velocity = 0;
                y_velocity = 0;
            }
            
            if (controller.bulletInChaimber()){
                //for (CoordinateSprite coordinateSprite : controller.getAllBullets()) {
                    if (!controller.moveObject(bulletdirY*5, bulletdirX*5 ,controller.getFirstBullet())){
                        bulletdirX = 0;
                        bulletdirY = 0;
                        controller.bulletHit();
                    
                
                
                }
            }
            view.repaint();
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
        if (once){
            movement.scheduleAtFixedRate(gametick, 0, 30, TimeUnit.MILLISECONDS);
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
                        controller.loadBullet(true);
                        break;
                }
            }
        }
        if (movementx == -1) {
            if (x_velocity > 0){
                friction = false;
            }
            System.out.println("left");
            controller.changeWalkingDirection(PlayerDirection.LEFT);
          
           
        }
        
        else if (movementx == 1) {
            if (x_velocity < 0){
                friction = false;
            }
            System.out.println("right");
            controller.changeWalkingDirection(PlayerDirection.RIGHT);         
            bulletdirX = 1;
            
        }
        if (movementy == 1) {
            if (y_velocity < 0){
                friction = false;
            }
            System.out.println("down");
            
            controller.changeWalkingDirection(PlayerDirection.DOWN);
            
            
        }
        else if (movementy == -1)  {
            if (y_velocity > 0){
                friction = false;
            }
            System.out.println("up");
            controller.changeWalkingDirection(PlayerDirection.UP);
            
            
        }
        x_velocity += movementx;
        y_velocity += movementy;
        if ((bulletdirX + bulletdirY) == 0){
            bulletdirX = movementx;
            bulletdirY = movementy;
        }
        FootType x = controller.getWalkingType();
        controller.changeFootType(x.next());

         
    }


    
    
    @Override
    public void keyReleased(KeyEvent event) {
        pressedKeys.remove(event.getKeyCode());
        friction = false;
        controller.changeFootType(FootType.WALK);
        controller.resetAcceleration();
        controller.changeFootType(FootType.STAND);
        controller.resetAcceleration();
        view.repaint();


        
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        //nothing
        
    }
    
}
