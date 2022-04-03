package inf101.game.view;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Line2D;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import inf101.backround.Floor;
import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;

public class Roomview extends JComponent {
    public iRoomview view;
    
    

    public Roomview(iRoomview myfloor){
        this.view = myfloor;
    }
    {
        this.setFocusable(true);
    }


    @Override
    public void paint(Graphics canvas1) {

        super.paintComponent(canvas1);
        this.setLayout(new BorderLayout());
        Graphics2D canvas = (Graphics2D) canvas1.create();
        int componentWidth = this.getWidth();
        int componentHeight = this.getHeight();
        this.drawBoard(canvas, 0, 0, componentWidth, componentHeight);
        

       
        
    }
    @Override
    public Dimension getPreferredSize() {
        int width = 700;
        int height = 750;
        return new Dimension(width, height);
    }

    public void drawBoard(Graphics canvas,int x, int y,int width, int height){
        
        this.drawPicture(canvas,29,60,width-59,height-110,"Floor1.png"); //drawroom
        this.drawWalls(canvas, width, height);
        this.drawPlayer(canvas,width,height);
        this.paintplayer(canvas,width,height);

    }

    /**
     * draws the walls, ive used a merged photo of 10 walls for the walls 
     * since drawing them one by one caused to mutch strain on my computer due
     * too resising 
     * @param canvas canvas to draw on
     * @param width width of canvas
     * @param height height of canvas
     */
    public void drawWalls(Graphics canvas,int width, int height){ 
        
        this.drawPicture(canvas, 0, 0, width, 60, "10_walls_top.png");// Upper wall
        this.drawPicture(canvas,0,0,30, height,"10_walls_left.png"); // right wall
        this.drawPicture(canvas,width-30,0,35,height,"10_walls2.png"); // left wall
        this.drawPicture(canvas, 0, height-50, width, 60, "10_walls_top.png");// Lower wall
        

    }
    


    
    public void drawRoom(Graphics canvas,int x,int y,int width, int height){
        canvas.setColor(Color.RED); // check if whole backround is drawn
        canvas.fillRect(x, y, height, width);
        for (itemWithCoordinate<Pixel> pixel : view.getPixels()) {
            int row = pixel.getCoordinate().getRow();
            int col = pixel.getCoordinate().getColumn();
            Color PixelColor = pixel.getItem().getColor();
            int tileX = x + col * width / this.view.getColumns() ; //inspired by sampleview
            int tileY = y + row * height / this.view.getRows();
            int nextTileX = x + (col + 1) * width /this.view.getColumns();
            int nextTileY = y + (row + 1) * height / this.view.getRows();
            int tileWidth = nextTileX - tileX;
            int tileHeight = nextTileY - tileY;
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor);

            
        }

    }
    

    public void drawPlayer(Graphics canvas,int width,int height){
        int x_Position = view.getCenter().getRow();
        int y_Position = view.getCenter().getColumn();
        for (itemWithCoordinate<Pixel> pixel : this.view.getSpritePixels()) {
            int row = pixel.getCoordinate().getRow();
            int col = pixel.getCoordinate().getColumn();
            Color PixelColor = pixel.getItem().getColor();
            int tileX = x_Position + col * width / this.view.getColumns() ; //inspired by sampleview
            int tileY = y_Position + row * height / this.view.getRows() ;
            int nextTileX = x_Position + (col + 1) * width /this.view.getColumns();
            int nextTileY = y_Position + (row + 1) * height / this.view.getRows();
            int tileWidth = nextTileX - tileX;
            int tileHeight = nextTileY - tileY;
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor); 
        }
    }

    private void drawPixel(Graphics canvas, int tileX, int tileY, int tileHeight, int tileWidth, Color PixelColor) {
        canvas.setColor(PixelColor);
        canvas.fillRect(tileX, tileY, tileWidth, tileHeight);
    }
    
    protected void paintplayer(Graphics g, int width, int height) {
    int y_cord = view.getPlayerSprite().getCoordinate().getRow();
    int x_cord = view.getPlayerSprite().getCoordinate().getColumn();
    int y_position = (int) (y_cord*3.75); //x position, board is 3.5 times smaller than width
    int x_position = (int) (x_cord*3.5); //y position, board is 3,75 times smaller than height
    Graphics2D canvas = (Graphics2D)g;
    x_position+=50; // +10 To move sprite over hitbox
    canvas.setStroke(new BasicStroke(5));
    //draw the head
    canvas.drawOval(x_position,y_position, width/35, height/35);
    // draw the body
    canvas.setStroke(new BasicStroke(5));
    canvas.drawLine(x_position+10, y_position+45, x_position+10, y_position+20);
    //draws legs
    FootType walking = view.getWalkingType();
    switch(walking){
        case STAND:
            canvas.drawLine(x_position+10, y_position+45, x_position+10, y_position+65);
            break;
        default:
            canvas.drawLine(x_position+10, y_position+45, x_position+20, y_position+65);
            canvas.drawLine(x_position+10, y_position+45, x_position, y_position+65);
            break;         
    }
    PlayerDirection direction = view.getPlayerDirection();
    switch (direction){
        case RIGHT:
        // draw the hand
        canvas.drawLine(x_position+10, y_position+30, x_position+20, y_position+30);
        //draw gun
        canvas.drawLine(x_position+20, y_position+30, x_position+20, y_position+25);
        canvas.drawLine(x_position+20, y_position+25, x_position+25, y_position+25);
        //draw eye
        break;
        default:
            canvas.drawLine(x_position+10, y_position+30, x_position, y_position+30);
            //draw gun
            canvas.drawLine(x_position+0, y_position+30, x_position+0, y_position+25);
            canvas.drawLine(x_position+0, y_position+25, x_position-5, y_position+25);
            break;
    }
    

    }

    protected void drawPicture(Graphics g,int x_position,int y_position,int width,int height, String filename){
        Graphics2D canvas = (Graphics2D)g;
        BufferedImage floor1;
        try {
            File file = new File("preben_bentdal_sem2_egenoppgave/src/main/java/inf101/game/view/Sprites/" + filename);
            FileInputStream fis = new FileInputStream(file);
            floor1 = ImageIO.read(fis);
            canvas.drawImage(floor1, x_position, y_position ,width ,height, this);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        

    }
    

}

    
