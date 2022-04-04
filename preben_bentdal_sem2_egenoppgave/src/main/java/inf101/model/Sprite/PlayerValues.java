package inf101.model.Sprite;

import java.awt.Color;

import inf101.model.Pixel;
import inf101.model.Sprite.hitBox;

public class PlayerValues {
    private Pixel pixels;
    
    private hitBox playerHitBox;
    private final int speed;
    private int acceleration;

    static final PlayerValues PLAYER_RIGHT = 
    new PlayerValues(new Pixel(Color.BLACK, true), hitBox.PLAYER_RIGHT ,1,1);

    static final PlayerValues PLAYER_LEFT = 
    new PlayerValues(new Pixel(Color.BLACK, true), hitBox.PLAYER_LEFT,1,1);


    static final PlayerValues PLAYER_STANDING_RIGHT = 
    new PlayerValues(new Pixel(Color.BLACK, true), hitBox.PLAYER_STANDING_RIGHT,1,1);
    
    static final PlayerValues PLAYER_STANDING_LEFT = 
    new PlayerValues(new Pixel(Color.BLACK, true), hitBox.PLAYER_STANDING_LEFT,1,1);
        
    

    private PlayerValues(Pixel pixels, hitBox playerHitBox,int speed,int acceleration ){
        this.pixels = pixels;
        this.playerHitBox = playerHitBox;
        this.speed = speed;
        this.acceleration = acceleration;
    }

    public boolean[][] getShape() {
        return this.playerHitBox.getValues();
    }

    public Pixel getPixels(){
        return this.pixels;
    }
    public int getWidth(){
        for (boolean[] bs : playerHitBox.getValues()) {
            int width = bs.length;
            return width;
        }
        return 0;
        


    }
    public int getHeight(){
        return playerHitBox.getValues().length;
    }

    public int getSpeed(){
        return this.speed;
    }
    public int getAcceleration(){
        return this.acceleration;
    }
    public void setAcceleration(){
        System.out.println("Accerelation = " + this.acceleration);
        this.acceleration++;
    }

    public void reset(){
        this.acceleration = 1;
    }
}