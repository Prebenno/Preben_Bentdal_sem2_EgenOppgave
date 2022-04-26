package inf101.model.Sprite;

import java.util.List;
import java.util.Objects;

public class Bullet {

    private int Xspeed;
    private int Yspped;
    private CoordinateSprite shape;




    public Bullet(int Xspeed, int Yspped, CoordinateSprite shape) {
        this.Xspeed = Xspeed;
        this.Yspped = Yspped;
        this.shape = shape;
    }

    public int getXspeed() {
        return this.Xspeed;
    }
    public int getYspeed() {
        return this.Yspped;
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
        return Xspeed == bullet.Xspeed && Yspped == bullet.Yspped;
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
