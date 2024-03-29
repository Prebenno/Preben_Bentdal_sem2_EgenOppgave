package inf101.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import inf101.game.States.Direction;
import inf101.game.States.GameState;
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
        //this.drawPicture(canvas1, componentHeight, componentHeight, componentHeight, componentHeight, "Player_standing_right.png");
        

       
        
    }
    @Override
    public Dimension getPreferredSize() {
        int width = 900;
        int height = 900;
        return new Dimension(width, height);
    }

    
    /**
     * This function draws the board, which includes the floor, walls, enemies, bullets, and the player
     * it also draws different things depending on the gameState
     * @param canvas the canvas to draw on
     * @param x the x coordinate of the top left corner of the board
     * @param y the y coordinate of the top left corner of the board
     * @param width the width of the screen
     * @param height the height of the screen
     */
    private void drawBoard(Graphics canvas,int x, int y,int width, int height){
        if (view.getGameState().equals(GameState.ACTIVE_GAME)){
            this.drawPicture(canvas,29,60,width-59,height-110,"Floor1.png"); //drawroom
            this.drawWalls(canvas, width, height);
            this.drawExtraInfo(canvas,width,height);
            if (view.enemyExists()){
                this.drawEnemies(canvas, width, height);
                }
            else{
                this.drawPowerUps(canvas,width,height);
                this.drawHitBox(canvas,width,height,view.getTrapDoor());}
            if (view.getPlayerSprite() != null){
                this.drawHitBox(canvas,width,height,view.getPlayerSprite());}
            if (view.isBulletGonnaShoot()){
                this.drawBullets(canvas,width, height);
                }
            }
        else if (view.getGameState().equals(GameState.GAME_OVER)){
            this.gameOver(canvas,width, height);
        }
        else if(view.getGameState().equals(GameState.PAUSED_GAME)){
            this.pause(canvas,width,height);
        }

    }

    /**
     * This function draws the powerups 
     * 
     * @param canvas The canvas to draw on
     * @param width The width of the screen
     * @param height The height of the screen
     */
    private void drawPowerUps(Graphics canvas,int width,int height){
        for (CoordinateSprite powerUp : view.getPowerUps()) {
            try {
                drawHitBox(canvas, width, height, powerUp);
            } catch (Exception e) {
                
            }
        }
    }
    /**
     * This function draws the floor number, score, and health of the player on the screen
     * 
     * @param canvas The Graphics object that you are drawing on.
     * @param width the width of the screen
     * @param height the height of the screen
     */
    private void drawExtraInfo(Graphics canvas, int width, int height){
        width = width/width+20;
        height = height/height+80;
        canvas.setFont(new Font("TimesRoman", Font.PLAIN, 16)); 
        canvas.setColor(Color.WHITE);
        GraphicHelperMethods.drawCenteredString(
            canvas, "Floor: " +  view.getFloorNum(),
            25, -25, width,height);
        GraphicHelperMethods.drawCenteredString(
            canvas, "Score: " +  view.getScore(),
            25, -10, width,height);
        GraphicHelperMethods.drawCenteredString(
            canvas, "Health: " +  view.getPlayerSprite().getHealth(),
            40, 5, width,height);
            
    }
    /**
     * This function draws the paused game screen
     * 
     * @param canvas The canvas to draw on
     * @param height height of board
     * @param widht width of board
     */
    private void pause(Graphics canvas, int height, int widht){
        canvas.setColor(Color.MAGENTA);
        canvas.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
        GraphicHelperMethods.drawCenteredString(canvas, "Game paused ",2, 2, height ,widht);  
        GraphicHelperMethods.drawCenteredString(canvas, "Press P to continue" ,2, 2, height ,widht+120);
        
    }

    /**
     * Draws the hitboxes of all the enemies in the game
     * 
     * @param canvas the canvas to draw on
     * @param width the width of the screen
     * @param height the height of the screen
     */
    private void drawEnemies(Graphics canvas, int width, int height){
        for (CoordinateSprite enemy : this.view.getEnemySprites()) {
            this.drawHitBox(canvas, width, height, enemy);
            
            }    
        }
    /**
     * draw the hitbox of all the bullets.
     * 
     * @param canvas The canvas to draw on
     * @param width the width of the screen
     * @param height the height of the screen
     */
    private void drawBullets(Graphics canvas,int width, int height){
        for (Bullet bullet : this.view.getAllBullets()){
            this.drawbullet(canvas, width, height, bullet);
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
    private void drawWalls(Graphics canvas,int width, int height){ 
        this.drawPicture(canvas, 0, 0, width, 60, "10_walls_top.png");// Upper wall
        this.drawPicture(canvas,0,0,30, height,"10_walls_left.png"); // right wall
        this.drawPicture(canvas,width-30,0,35,height,"10_walls2.png"); // left wall
        this.drawPicture(canvas, 0, height-50, width, 60, "10_walls_top.png");// Lower wall
    }
    
    /**
     * This function draws the hitbox of the object passed in
     * 
     * @param canvas the canvas to draw on
     * @param width the width of the canvas
     * @param height the height of the canvas
     * @param object the object that you want to draw the hitbox for
     */
    private <E> void drawHitBox(Graphics canvas,int width,int height,E object){
        int x_Position = view.getCenter().getRow();
        int y_Position = view.getCenter().getColumn();
        CoordinateSprite sprite = null;
        Direction direction = null;
        if (object instanceof Bullet){
            y_Position+=5;
            x_Position+=5;
            Bullet bullet = ((Bullet) object);
            sprite = bullet.getShape();
            direction = sprite.getDirection();
        }
        else{
            sprite = (CoordinateSprite) object;
        }
        if (sprite != null){
            direction = sprite.getDirection();}
            int row = sprite.getCoordinate().getRow();
            int col = sprite.getCoordinate().getColumn();
            
            int tileX = x_Position + col * width / this.view.getColumns() ; //inspired by sampleview
            int tileY = y_Position + row * height / this.view.getRows() ;
        if (sprite.equals(view.getTrapDoor())){
            this.drawPicture(canvas, width/2, height/2, width/11, height/11, "floor_ladder.png");
        }
        else{
            String right_png;
            String left_png;
            String down_png;
            String up_png;
            switch (sprite.getFootType()){
                case STAND:
                    right_png = sprite.getEntity().right_png_standing;
                    left_png = sprite.getEntity().left_png_standing;
                    down_png = sprite.getEntity().down_png_standing;
                    up_png = sprite.getEntity().up_png_standing;
                    break;
                default:
                    right_png = sprite.getEntity().right_png;
                    left_png = sprite.getEntity().left_png;
                    down_png = sprite.getEntity().down_png;
                    up_png  =sprite.getEntity().up_png;
                    break;
                }
            this.drawDirectionObject(canvas, tileX, tileY, width, height,
            right_png,
            left_png,
            down_png,
            up_png,
            direction);
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
     * This function draws a picture of a object with respect to direction on the canvas
     * 
     * @param canvas the canvas to draw on
     * @param x_position the x position of the object
     * @param y_position the y coordinate of the object
     * @param tileWidth the width of the tile
     * @param tileHeight the height of the tile
     * @param right_png the image file for the right direction
     * @param left_png the image file for the left direction
     * @param down_png the name of the image file for the down direction
     * @param up_png the image file for the up direction
     * @param direction the direction of the object
     */
    private void drawDirectionObject(Graphics canvas, int x_position, int y_position, int tileWidth, int tileHeight,String right_png,String left_png, String down_png, String up_png,Direction direction) {  
        int height = tileHeight/16;  
        int width = tileWidth / 23;
        x_position -=3;
        String png = "";
        switch (direction) {
            case LEFT:
                png = left_png;
                break;
            case UP:
                png =up_png;
                break;
            case DOWN:
                png =down_png;
                break;
            default:
                png = right_png;
                break;
        }
        if (png !=null){
            this.drawPicture(canvas, x_position, y_position, width, height, png);}
        

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
    private void drawPicture(Graphics g,int x_position,int y_position,int width,int height, String filename){
        Graphics2D canvas = (Graphics2D)g;
        BufferedImage image;
        try {
            File file = new File("preben_bentdal_sem2_egenoppgave/src/main/java/inf101/game/view/Sprites/" + filename);
            FileInputStream fis = new FileInputStream(file);
            image = ImageIO.read(fis);
            canvas.drawImage(image, x_position, y_position ,width ,height, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void gameOver(Graphics canvas ,int x ,int y){
        Color over = new Color (0,0,0,128);
        canvas.setColor(over);
        canvas.fillRect(x+15, y+15, x+15, y+15);  
        
        canvas.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        GraphicHelperMethods.drawCenteredString(
                canvas, "Game over, total score: " +view.getScore(),
                2, 2, x ,y); 
    }

    private void drawbullet(Graphics canvas, int width, int height, Bullet bullet){
    int x_Position = view.getCenter().getRow()+9;
    int y_Position = view.getCenter().getColumn()+9;
    for (itemWithCoordinate<Pixel> pixel : bullet.getShape()) {
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

    /*
     * Old function for drawing the hitbox of the object, i dont have the hearth to delete it so
     * ill save it here at the end.
     
    public <E> void drawHitBox2(Graphics canvas,int width,int height,E object){
        int x_Position = view.getCenter().getRow();
        int y_Position = view.getCenter().getColumn();
        CoordinateSprite sprite = null;
        Direction direction = null;
        if (object instanceof Bullet){
            y_Position+=5;
            x_Position+=5;
            Bullet bullet = ((Bullet) object);
            sprite = bullet.getShape();
            direction = sprite.getDirection();
        }
        else{
            sprite = (CoordinateSprite) object;
            if (sprite != null){
                direction = sprite.getDirection();}
        }
        boolean once = true;
        for (itemWithCoordinate<Pixel> pixel : sprite) {
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
                if (sprite.equals(view.getTrapDoor())){
                    this.drawPicture(canvas, width/2, height/2, width/11, height/11, "floor_ladder.png");
                }
                else{
                    String right_png;
                    String left_png;
                    String down_png;
                    String up_png;
                    switch (sprite.getFootType()){
                        case STAND:
                            right_png = sprite.getEntity().right_png_standing;
                            left_png = sprite.getEntity().left_png_standing;
                            down_png = sprite.getEntity().down_png_standing;
                            up_png = sprite.getEntity().up_png_standing;
                            break;
                        default:
                            right_png = sprite.getEntity().right_png;
                            left_png = sprite.getEntity().left_png;
                            down_png = sprite.getEntity().down_png;
                            up_png  =sprite.getEntity().up_png;
                            break;
                        }
                    this.drawDirectionObject(canvas, tileX, tileY, width, height,
                    right_png,
                    left_png,
                    down_png,
                    up_png,
                    direction);
                }
                once = false;
            }
            this.drawPixel(canvas, tileX, tileY, tileHeight, tileWidth,PixelColor); 
        }   
    }
     */

}



    
