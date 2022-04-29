package inf101.model.Sprite;

import java.util.Objects;

public class Bullet {

    private int Xspeed;
    private int Yspped;
    private CoordinateSprite shape;
    private boolean enemyshot;
    private int damage;
    
    



    public Bullet(int Xspeed, int Yspped, CoordinateSprite shape,boolean shot,int damage) {
        this.Xspeed = Xspeed;
        this.Yspped = Yspped;
        this.shape = shape;
        this.enemyshot = shot;
        this.damage =damage;
    }

    public boolean isPlayerBullet(){
        return this.enemyshot;
    }
    public int getXspeed() {
        return this.Xspeed;
    }
    public int getYspeed() {
        return this.Yspped;
    }
    public int getDamage(){
        return this.damage;
    }

    public CoordinateSprite getShape(){
        return this.shape;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Bullet)) {
            return false;
        }
        Bullet bullet = (Bullet) o;
        return Xspeed == bullet.Xspeed && Yspped == bullet.Yspped && Objects.equals(shape, bullet.shape) && enemyshot == bullet.enemyshot;
    }
   

    @Override
    public int hashCode() {
        return Objects.hash(Xspeed, Yspped);
    }

    @Override
    public String toString() {
        return "{" +
            " Xspeed='" + getXspeed() + "'" +
            ", Yspped='" + getYspeed() + "'" +
            "}";
    }

    
    
}
