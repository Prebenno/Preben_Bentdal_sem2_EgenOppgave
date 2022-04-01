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
    public int speed;
    public boolean foot;

    public GameController(IGameController controller, Roomview view){
        this.controller = controller;
        this.view = view;
        this.view.addKeyListener(this);
        this.speed=5;
        
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //nothing
        
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            controller.movePlayer(0, -6);
            controller.changeWalkingDirection(PlayerDirection.LEFT);
            System.out.println("lol");
            
        }
        else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.movePlayer(0, 6);
            controller.changeWalkingDirection(PlayerDirection.RIGHT);
        }
        else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            controller.movePlayer(6, 0);
        }
        else if (event.getKeyCode() == KeyEvent.VK_UP) {
            controller.movePlayer(-6, 0);
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
        controller.changeFootType(FootType.STAND);
        view.repaint();


        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        //nothing
        
    }
    
}
