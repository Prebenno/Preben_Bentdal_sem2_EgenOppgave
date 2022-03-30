package inf101.game;

import javax.swing.JFrame;

import inf101.backround.Floor;
import inf101.game.controller.GameController;
import inf101.game.view.Roomview;
import inf101.grid.OutOfBoundsException;
import inf101.model.GameModel;

public class GameMain {
    public static final String WINDOW_TITLE = "INF101 Pyramid game";

    public static void main(String[] args) throws OutOfBoundsException{
        GameModel myfloor = new GameModel();
        Roomview view = new Roomview(myfloor);
        JFrame frame = new JFrame(WINDOW_TITLE);
        GameController gameController = new GameController(myfloor, view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Here we set which component to view in our window
        frame.setContentPane(gameController.view);

        // Call these methods to actually display the window
        frame.pack();
        frame.setVisible(true);
    
    }
        

        
}
    

