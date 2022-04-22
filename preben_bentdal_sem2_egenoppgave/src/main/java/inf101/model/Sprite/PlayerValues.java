package inf101.model.Sprite;

import java.awt.Color;
import java.util.Objects;

import inf101.model.Pixel;
import inf101.model.Sprite.hitBox;

public class PlayerValues {
    private Pixel pixels;
    
    private hitBox playerHitBox;
    private final int speed;
    private int acceleration;

    static final PlayerValues PLAYER = 
    new PlayerValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,1,1);


    public static final PlayerValues BULLET = new PlayerValues(new Pixel(Color.BLUE, true), hitBox.BULLET,1,1);
        
    

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

    


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlayerValues)) {
            return false;
        }
        PlayerValues playerValues = (PlayerValues) o;
        return Objects.equals(pixels, playerValues.pixels) && Objects.equals(playerHitBox, playerValues.playerHitBox) && speed == playerValues.speed && acceleration == playerValues.acceleration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixels, playerHitBox, speed, acceleration);
    }

}