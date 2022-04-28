package inf101.model.Sprite;

import java.awt.Color;
import java.util.Objects;

import inf101.model.Pixel;
import inf101.model.Sprite.hitBox;

public class PlayerValues {
    private Pixel pixels;
    
    private hitBox spriteHitBox;
    private final int speed;
    private int acceleration;
    private boolean canShoot;

    public String right_png;
    public String left_png;
    public String down_png;
    public String up_png;

        static final PlayerValues PLAYER = 
        new PlayerValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,1,1,
        "Skeleton_looking_right-removebg-preview.png",
        "Skeleton_looking_left-removebg-preview.png",
        "Skeleton_looking_down.png",
        "Skeleton_looking_up-removebg-preview.png",
        false);

        static final PlayerValues RUNNER = 
        new PlayerValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,1,1,
        "Skeleton_looking_right-removebg-preview.png",
        "Skeleton_looking_left-removebg-preview.png",
        "Skeleton_looking_down.png",
        "Skeleton_looking_up-removebg-preview.png",
        false);

        public static final PlayerValues SHOOTER = 
        new PlayerValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,2,1,
        "Skeleton_looking_right-removebg-preview.png",
        "Skeleton_looking_left-removebg-preview.png",
        "Skeleton_looking_down.png",
        "Skeleton_looking_up-removebg-preview.png",
        true);




    public static final PlayerValues BULLET = new PlayerValues(new Pixel(Color.BLUE, true), hitBox.BULLET,1,1,null,null,null,null,false);
    // TODO fix etterop
    public static final PlayerValues TRAPDOOR = new PlayerValues(new Pixel(Color.BLUE, false), hitBox.TRAPDOOR,0,0,"floor_ladder.png","floor_ladder.png","floor_ladder.png","floor_ladder.png",false);
    

    PlayerValues(Pixel pixels, hitBox playerHitBox,int speed,int acceleration,String right_png,String left_png,String down_png,String up_png,boolean canshoot ){
        this.pixels = pixels;
        this.spriteHitBox = playerHitBox;
        this.speed = speed;
        this.acceleration = acceleration;
        this.right_png = right_png;
        this.left_png = left_png;
        this.up_png = up_png;
        this.down_png = down_png;
        this.canShoot = canShoot;
    }
    
    public boolean[][] getShape() {
        return this.spriteHitBox.getValues();
    }
    public String getUpPng(){
        return this.up_png;
    }
    public String getDownPng(){
        return this.down_png;
    }
    public String getLeftPng(){
        return this.left_png;
    }
    public String getRightPng(){
        return this.right_png;
    }

    public Pixel getPixels(){
        return this.pixels;
    }
    public int getWidth(){
        for (boolean[] bs : spriteHitBox.getValues()) {
            int width = bs.length;
            return width;
        }
        return 0;
        


    }
    public int getHeight(){
        return spriteHitBox.getValues().length;
    }

    


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PlayerValues)) {
            return false;
        }
        PlayerValues playerValues = (PlayerValues) o;
        return Objects.equals(pixels, playerValues.pixels) && Objects.equals(spriteHitBox, playerValues.spriteHitBox) && speed == playerValues.speed && acceleration == playerValues.acceleration;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pixels, spriteHitBox, speed, acceleration);
    }

}