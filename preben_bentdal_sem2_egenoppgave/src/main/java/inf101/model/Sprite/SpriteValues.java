package inf101.model.Sprite;

import java.awt.Color;
import java.util.Objects;

import inf101.model.Pixel;
import inf101.model.Sprite.hitBox;

public class SpriteValues {
    private Pixel pixels;
    
    private hitBox spriteHitBox;
    private final int speed;
    private int movementCount;
    
    /**
     * Stores the images for all different types of walking
     * all images in sprites is resised to 42/36 to make the program run easier
     */
    public String right_png;
    public String left_png;
    public String down_png;
    public String up_png;
    public String right_png_standing;
    public String left_png_standing;
    public String up_png_standing ;
    public String down_png_standing;

        static final SpriteValues PLAYER = 
        new SpriteValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,2,
        "Player_walking_right.png",
        "Player_walking_left.png",
        "Player_walking_down.png",
        "Player_walking_up.png",
        "Player_standing_right.png",
        "Player_standing_left.png",
        "Player_standing_down.png",
        "Player_standing_up.png");

        static final SpriteValues RUNNER = 
        new SpriteValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,1,
        "Skeleton_walking_right.png",
        "Skeleton_walking_left.png",
        "Skeleton_walking_down.png",
        "Skeleton_walking_up.png",
        "Skeleton_standing_right.png",
        "Skeleton_standing_left.png",
        "Skeleton_standing_down.png",
        "Skeleton_standing_up.png");

        public static final SpriteValues SHOOTER = 
        new SpriteValues(new Pixel(Color.RED, true), hitBox.PLAYER_HITBOX ,2,
        "mage_standing_right.png",
        "mage_standing_left.png",
        "mage_standing_down.png",
        "mage_standing_up.png",
        "mage_standing_right.png",
        "mage_standing_left.png",
        "mage_standing_down.png",
        "mage_standing_up.png");




    public static final SpriteValues BULLET = new SpriteValues(new Pixel(Color.BLUE, true), hitBox.BULLET,1,null,null,null,null,null,null,null,null);

    public static final SpriteValues TRAPDOOR = new SpriteValues(new Pixel(Color.BLUE, false), hitBox.TRAPDOOR,"floor_ladder.png");
    
    public static final SpriteValues HEALTH = new SpriteValues(new Pixel(Color.BLUE,false), hitBox.BULLET,"Health_potion.png");

    public static final SpriteValues ATTACKSPEED = new SpriteValues(new Pixel(Color.BLUE,false), hitBox.BULLET,"Attack_speed_potion.png");

    public static final SpriteValues DAMAGEUP = new SpriteValues(new Pixel(Color.BLUE,false), hitBox.BULLET,"Attack_potion.png");

    public static final SpriteValues SCOREMULTI = new SpriteValues(new Pixel(Color.BLUE,false), hitBox.BULLET,"Score_multiplier_potion.png");

    
    SpriteValues(Pixel pixels, hitBox spriteHitBox,int speed,String right_png,String left_png,String down_png,String up_png,String right_png_standing,String left_png_standing,String down_png_standing,String up_png_standing ){
        this.pixels = pixels;
        this.spriteHitBox = spriteHitBox;
        this.speed = speed;
        this.right_png = right_png;
        this.left_png = left_png;
        this.up_png = up_png;
        this.down_png = down_png;
        this.right_png_standing = right_png_standing;
        this.left_png_standing = left_png_standing;
        this.up_png_standing = up_png_standing;
        this.down_png_standing = down_png_standing;
    }
    SpriteValues(Pixel pixels,hitBox spriteHitBox, String png){
        this.pixels = pixels;
        this.spriteHitBox = spriteHitBox;
        this.right_png =  png;
        this.left_png = png;
        this.up_png = png;
        this.down_png = png;
        this.right_png_standing = png;
        this.left_png_standing = png;
        this.up_png_standing = png;
        this.down_png_standing = png;
        this.speed = 0;
    }
    
    public int getSpeed(){
        return this.speed;
    }
    public int getTotalMovements(){
        return this.movementCount;
    }
    public void addOneStep(){
        this.movementCount++;
    }
    public boolean[][] getShape() {
        return this.spriteHitBox.getValues();
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
        if (!(o instanceof SpriteValues)) {
            return false;
        }
        SpriteValues spriteValues = (SpriteValues) o;
        return Objects.equals(pixels, spriteValues.pixels) && Objects.equals(spriteHitBox, spriteValues.spriteHitBox) && speed == spriteValues.speed && movementCount == spriteValues.movementCount && Objects.equals(right_png, spriteValues.right_png) && Objects.equals(left_png, spriteValues.left_png) && Objects.equals(down_png, spriteValues.down_png) && Objects.equals(up_png, spriteValues.up_png) && Objects.equals(right_png_standing, spriteValues.right_png_standing) && Objects.equals(left_png_standing, spriteValues.left_png_standing) && Objects.equals(up_png_standing, spriteValues.up_png_standing) && Objects.equals(down_png_standing, spriteValues.down_png_standing);
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(pixels, spriteHitBox, speed);
    }

}