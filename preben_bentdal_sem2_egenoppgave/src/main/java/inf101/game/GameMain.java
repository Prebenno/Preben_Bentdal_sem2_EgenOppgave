package inf101.game;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import inf101.backround.Floor;
import inf101.game.controller.GameController;
import inf101.game.view.Roomview;
import inf101.grid.OutOfBoundsException;
import inf101.model.GameModel;

//Mutch of the code here was gotten from
//https://stackoverflow.com/questions/28809000/how-to-insert-jpanel-beside-a-grid
public class GameMain {
    public JFrame window;
    public String WINDOW_TITLE = "INF101 Pyramid game";
    public JPanel ui;
    public JPanel gameGrid;
    public JPanel WallPanel;
    public GameModel gameRoom;
    public GameController controller;

    public GameMain() throws OutOfBoundsException{
        initUi();
    }

    private void initUi() throws OutOfBoundsException {
        JFrame frame = new JFrame("INF101 Pyramid game");
        this.window = frame;
        this.ui = new JPanel(new BorderLayout(5, 5));
        ui.setBackground(Color.YELLOW);
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));
        createGame();
        createWall();
        this.window.add(WallPanel,BorderLayout.NORTH);
        this.ui.add(gameGrid,BorderLayout.CENTER);
        
        
        

    }
    public JPanel getUi(){
        return this.ui;

    }

    public void createWall() throws OutOfBoundsException{
        JPanel panel1 = new JPanel();
        GameModel myfloor = new GameModel(true);
        Roomview view = new Roomview(myfloor);
        panel1.setPreferredSize(new Dimension(100,100));
        panel1.add(view,BorderLayout.NORTH);
        this.WallPanel = panel1;

    }
    public  void createGame() throws OutOfBoundsException{
        JPanel panel1 = new JPanel();
        GameModel myfloor = new GameModel();
        Roomview view = new Roomview(myfloor);
        GameController gameController = new GameController(myfloor, view);
        this.controller = gameController;
        panel1.setPreferredSize(new Dimension(750,700));
        panel1.add(view,BorderLayout.CENTER);
        panel1.addKeyListener(gameController);
        this.gameGrid = panel1;
        
    }

    private static void initGrid() throws OutOfBoundsException{
        JFrame frame = new JFrame("INF101 Pyramid game");
        
        frame.setSize(800,700);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GameMain().getUi());
        frame.setLocationByPlatform(true);
        frame.setVisible(true);  // should be last
    }

   
    public static void main(String[] args) throws OutOfBoundsException{

        JPanel container = new JPanel(new GridLayout(2,2)); //
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JFrame frame = new JFrame("INF101 Pyramid game");
        panel1.setBackground(Color.YELLOW);
        panel1.setBorder(new EmptyBorder(4, 4, 4, 4));
       
        GameModel myfloor = new GameModel();
        Roomview view = new Roomview(myfloor);

        GameController gameController = new GameController(myfloor, view);
        
        panel1.add(gameController.view,BorderLayout.CENTER);


        GameModel myfloor1 = new GameModel(true);
        Roomview view1 = new Roomview(myfloor1);
        panel2.setPreferredSize(new Dimension(100,100));
        panel2.add(view1,BorderLayout.NORTH);
       
        
        container.add(panel1);
        container.add(panel2);
        
        
        
        frame.setSize(800,700);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setContentPane(new GameMain().getUi());
        frame.setContentPane(container);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);  // should be last

        
        
    }
                
}

    

