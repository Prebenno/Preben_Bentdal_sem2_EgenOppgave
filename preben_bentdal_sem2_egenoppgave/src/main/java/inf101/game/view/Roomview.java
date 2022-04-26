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
import inf101.game.States.Direction;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

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
        int width = 900;
        int height = 900;
        return new Dimension(width, height);
    }

    /**
     * This function draws the board, which includes the room, the walls, the hitboxes, and the player
     * 
     * @param canvas the canvas to draw on
     * @param x the x coordinate of the top left corner of the board
     * @param y the y coordinate of the top left corner of the board
     * @param width the width of the window
     * @param height the height of the window
     */
    public void drawBoard(Graphics canvas,int x, int y,int width, int height){
        
        this.drawPicture(canvas,29,60,width-59,height-110,"Floor1.png"); //drawroom
        this.drawWalls(canvas, width, height);
        this.drawTrapBoard(canvas,width,height);
        if (view.getPlayerSprite() != null){
            this.drawHitBox(canvas,width,height);}
        if (view.enemyExists()){
            this.drawEnemies(canvas, width, height);}
        
        if (view.isBulletGonnaShoot()){
            this.drawBulletHitBox(canvas,width,height);}
        //this.drawPlayer(canvas,width,height);

    }

    public void drawEnemies(Graphics canvas, int width, int height){
        for (CoordinateSprite enemy : this.view.getEnemySprite()) {
            this.drawEnemyHitBox(canvas, width, height,enemy);
            
            }    
        }
    public void drawTrapBoard(Graphics canvas, int width, int height){
        boolean once = true;
        int x_Position = view.getCenter().getRow()+20;
        int y_Position = view.getCenter().getColumn()+10;
        for (itemWithCoordinate<Pixel> pixel : view.getTrapDoor()) {
            int row = pixel.getCoordinate().getRow();
            int col = pixel.getCoordinate().getColumn();
            
            Color PixelColor = pixel.getItem().getColor();
            int tileX = x_Position + col * width / this.view.getColumns() ; //inspired by sampleview
            int tileY = y_Position + row * height / this.view.getRows() ;
            int nextTileX = x_Position + (col + 1) * width /this.view.getColumns();
            int nextTileY = y_Position + (row + 1) * height / this.view.getRows();
            int tileWidth = nextTileX - tileX;
            int tileHeight = nextTileY - tileY;
            if (once){
                this.drawPicture(canvas, width/2, height/2, width/11, height/11, "floor_ladder.png");
                once = false;
            }
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor); 
        }

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
    
    
    /**
     * This function draws the room by drawing the background and then drawing each pixel in the room
     * 
     * @param canvas the Graphics object that you use to draw on the JPanel
     * @param x the x coordinate of the upper left corner of the rectangle to draw the room in
     * @param y the y coordinate of the top left corner of the rectangle to draw
     * @param width the width of the room
     * @param height the height of the room
     */
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
    
    
    /**
     * This function draws the hitbox of the player
     * 
     * @param canvas the canvas to draw on
     * @param width the width of the screen
     * @param height the height of the screen
     */
    public void drawHitBox(Graphics canvas,int width,int height){
        int x_Position = view.getCenter().getRow();
        int y_Position = view.getCenter().getColumn();
        Direction direction = this.view.getPlayerSprite().getDirection();
        boolean once = true;
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
            if (once){
                this.drawPlayer(canvas, tileX, tileY, width, height,
                "Skeleton_looking_right-removebg-preview.png",
                "Skeleton_looking_left-removebg-preview.png",
                "Skeleton_looking_down.png",
                "Skeleton_looking_up-removebg-preview.png",
                direction);
                once = false;
                
            }
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor); 
        }
        
    }

    /**
     * This function draws the enemy's hitbox, which is a collection of pixels that are used to
     * determine if the enemy has been hit by the player's attack
     * 
     * @param canvas the canvas to draw on
     * @param width the width of the canvas
     * @param height the height of the canvas
     */
    public void drawEnemyHitBox(Graphics canvas,int width,int height,CoordinateSprite enemy){
        int x_Position = view.getCenter().getRow();
        int y_Position = view.getCenter().getColumn();
        Direction direction = enemy.getDirection();
        boolean once = true;
        for (itemWithCoordinate<Pixel> pixel : enemy) {
            int row = pixel.getCoordinate().getRow();
            int col = pixel.getCoordinate().getColumn();
            
            Color PixelColor = pixel.getItem().getColor();
            int tileX = x_Position + col * width / this.view.getColumns() ; //inspired by sampleview
            int tileY = y_Position + row * height / this.view.getRows() ;
            int nextTileX = x_Position + (col + 1) * width /this.view.getColumns();
            int nextTileY = y_Position + (row + 1) * height / this.view.getRows();
            int tileWidth = nextTileX - tileX;
            int tileHeight = nextTileY - tileY;
            if (once){
                this.drawPlayer(canvas, tileX, tileY, width, height,
                "Skeleton_looking_right-removebg-preview.png",
                "Skeleton_looking_left-removebg-preview.png",
                "Skeleton_looking_down.png",
                "Skeleton_looking_up-removebg-preview.png",
                direction);
                once = false;
            }
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor); 
        }
        
    }
    //private void drawBullet(Graphics canvas, int x_position, int y_position, int tileWidth, int tileHeight) {

    
    /**
     * This function draws the bullet hitbox by iterating through the bullet's shape and drawing each
     * pixel in the shape
     * 
     * @param canvas the canvas to draw on
     * @param width the width of the canvas
     * @param height the height of the canvas
     */
    public void drawBulletHitBox(Graphics canvas, int width,int height){
            int x_Position = view.getCenter().getRow()+5;
            int y_Position = view.getCenter().getColumn()+5;
            for (Bullet bullet : this.view.getAllBullets()) {
                
            
                for (itemWithCoordinate<Pixel> pixel : bullet.getShape()){ 
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

    }
   
    /**
     * Draw a pixel on the canvas
     * 
     * @param canvas The canvas to draw on
     * @param tileX The x coordinate of the tile
     * @param tileY The y coordinate of the tile
     * @param tileHeight The height of the tile
     * @param tileWidth The width of the tile
     * @param PixelColor The color of the pixel
     */
    private void drawPixel(Graphics canvas, int tileX, int tileY, int tileHeight, int tileWidth, Color PixelColor) {
        canvas.setColor(PixelColor);
        canvas.fillRect(tileX, tileY, tileWidth, tileHeight);
    }

    /**
     * This function draws the player on the screen
     * 
     * @param canvas the canvas to draw on
     * @param x_position the x position of the player
     * @param y_position The y position of the tile
     * @param tileWidth the width of the tile
     * @param tileHeight The height of the tile in pixels
     */
    private void drawPlayer(Graphics canvas, int x_position, int y_position, int tileWidth, int tileHeight,String right_png,String left_png, String down_png, String up_png,Direction direction) {
        
        int height = tileHeight/16;  
        int width  = tileWidth /16;
        x_position -=13;
        String png = "";
        switch (direction) {
            case RIGHT:
                png = right_png;
                width = tileWidth / 22;
                x_position+=10;
                break;
            case UP:
                png =up_png;
                width = tileWidth / 22;
                x_position+=10;
                break;
                
           
            case DOWN:
                png =down_png;
                width = tileWidth / 22;
                x_position+=10;
                break;
        
            default:
                png = left_png;
                break;

        }
        
        this.drawPicture(canvas, x_position, y_position, width, height, png);
        

    }
    
    /**
     * It takes a graphics object, an x and y position, a width and height, and a filename, and draws
     * the image with the given filename to the graphics object at the given x and y position, with the
     * given width and height
     * 
     * @param g The graphics object
     * @param x_position The x-coordinate of the upper left corner of the picture.
     * @param y_position The y-coordinate of the top left corner of the picture.
     * @param width The width of the picture
     * @param height The height of the picture
     * @param filename The name of the file you want to draw.
     */
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
    /*
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
    FootType walking = FootType.STAND;
    switch(walking){
        case STAND:
            canvas.drawLine(x_position+10, y_position+45, x_position+10, y_position+65);
            break;
        default:
            canvas.drawLine(x_position+10, y_position+45, x_position+20, y_position+65);
            canvas.drawLine(x_position+10, y_position+45, x_position, y_position+65);
            break;         
    }
    PlayerDirection direction = PlayerDirection.UP;
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
    */
    

   

}

    
