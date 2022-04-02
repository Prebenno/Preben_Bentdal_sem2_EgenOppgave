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

        GridBagLayout gridLayout = new GridBagLayout();
        JPanel container = new JPanel(); 
        container.setLayout(gridLayout);


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setPreferredSize(new Dimension(750,700));
        panel2.setPreferredSize(new Dimension(20,700));
        panel3.setPreferredSize(new Dimension(20,700));
        panel4.setPreferredSize(new Dimension(750,20));
        panel5.setPreferredSize(new Dimension(750,20));
        JFrame frame = new JFrame("INF101 Pyramid game");
        
        
        
       
        GameModel myfloor = new GameModel();
        Roomview view = new Roomview(myfloor);

        GameController gameController = new GameController(myfloor, view);
        
        panel1.add(gameController.view);


        GameModel myfloor1 = new GameModel(true);
        Roomview view1 = new Roomview(myfloor1);

        GameModel myfloor2 = new GameModel(true);
        Roomview view2 = new Roomview(myfloor2);

        GameModel myfloor3 = new GameModel(true);
        Roomview view3 = new Roomview(myfloor3);

        GameModel myfloor4 = new GameModel(true);
        Roomview view4 = new Roomview(myfloor4);
        panel2.add(view1);

        panel3.add(view2);

        panel4.add(view1);

        panel5.add(view1);
       

        GridBagConstraints gbc = new GridBagConstraints();
        //gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
    
        container.add(panel1,gbc);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        gbc.gridy = 1;
      
        container.add(panel2,gbc);
        gbc.anchor = GridBagConstraints.LINE_END;
        

        gbc.gridx = 2;
        gbc.gridy = 1;
  
        container.add(panel3,gbc);
        gbc.anchor = GridBagConstraints.PAGE_START;

        gbc.gridx = 1;
        gbc.gridy = 0;

        container.add(panel4,gbc);
        gbc.anchor = GridBagConstraints.PAGE_END;
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        
        container.add(panel5,gbc);

        
        
        
        //frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setContentPane(new GameMain().getUi());
        frame.setContentPane(container);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);  // should be last

        
        
    }
                
}

    

