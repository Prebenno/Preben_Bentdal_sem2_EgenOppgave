package inf101.game;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import inf101.backround.Floor;
import inf101.game.controller.GameController;
import inf101.game.view.Roomview;
import inf101.grid.OutOfBoundsException;
import inf101.model.GameModel;


public class GameMain {
    
    public static void main(String[] args) throws OutOfBoundsException{
        JFrame frame = new JFrame("INF101 Pyramid game");
        GameModel myfloor = new GameModel();
        Roomview view = new Roomview(myfloor);
        GameController gameController = new GameController(myfloor, view);        
        frame.setSize(800,700);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setContentPane(new GameMain().getUi());
        frame.setContentPane(gameController.view);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);  // should be last        
        
    }
                
}
    

