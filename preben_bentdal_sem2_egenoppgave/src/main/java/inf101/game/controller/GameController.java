package inf101.game.controller;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.game.view.Roomview;

import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController implements KeyListener,ActionListener {

    public IGameController controller;
    public Roomview view;
    public boolean foot;
    public int x_velocity;
    public int y_velocity;

    public GameController(IGameController controller, Roomview view){
        this.controller = controller;
        this.view = view;
        this.view.addKeyListener(this);

        
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //nothing
        
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            controller.movePlayer(0, -1);
            controller.changeWalkingDirection(PlayerDirection.LEFT);
            x_velocity-=1;
        }
            
        
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.movePlayer(0, 1);
            controller.changeWalkingDirection(PlayerDirection.RIGHT);
            x_velocity++;
        }
        else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            controller.movePlayer(1, 0);
            controller.changeWalkingDirection(PlayerDirection.DOWN);
            y_velocity++;
        }
        else if (event.getKeyCode() == KeyEvent.VK_UP) {
            controller.movePlayer(-1, 0);
            controller.changeWalkingDirection(PlayerDirection.UP);
            y_velocity-=1;
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            // Spacebar was pressed
        }
        FootType x = controller.getWalkingType();
        controller.changeFootType(x.next());
        view.repaint();
         
    }


    
    
    @Override
    public void keyReleased(KeyEvent event) {
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
