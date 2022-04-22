package inf101.model.Sprite;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate startPos;
    

    public void setSenterColumn(){
        this.startPos = new Coordinate(0,0); // player spawnpoint not on walls

    }
    public CoordinateSprite getStarterSprite(){
        return getPlayer(new Coordinate(50,50),100);
    }
    public CoordinateSprite getStarterSprite2(){
        return getPlayer(new Coordinate(150,150),100);
    }

    public CoordinateSprite getPlayer(Coordinate position, int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (PlayerValues.PLAYER, position ,health);
        return myCordSprite;

    }
    public CoordinateSprite getBulletSprite(Coordinate position){
        CoordinateSprite myBullet = new CoordinateSprite (PlayerValues.BULLET, position,0);
        return myBullet;
    }
}
