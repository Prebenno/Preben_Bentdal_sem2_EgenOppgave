package inf101.game.controller;

import inf101.game.view.Roomview;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController implements KeyListener,ActionListener {

    public IGameController controller;
    public Roomview view;
    public int speed;

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
            controller.movePlayer(0, -1);
        }
        else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            controller.movePlayer(0, 1);
        }
        else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
            controller.movePlayer(1, 0);
        }
        else if (event.getKeyCode() == KeyEvent.VK_UP) {
            controller.movePlayer(-1, 0);
        }
        else if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            // Spacebar was pressed
        }
        view.repaint();
         
    }


    
    
    @Override
    public void keyReleased(KeyEvent arg0) {
        //nothing
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        //nothing
        
    }
    
}
