package inf101.model.Sprite;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate startPos;
    

    public void setSenterColumn(){
        this.startPos = new Coordinate(0,0); // player spawnpoint not on walls

    }
    public CoordinateSprite getStarterSprite(){
        return getRightWalkingPlayerPos(new Coordinate(50,50),100);
    }

    public CoordinateSprite getRightWalkingPlayerPos(Coordinate position, int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (PlayerValues.PLAYER_RIGHT, position ,health);
        return myCordSprite;

    }
    public CoordinateSprite getLeftWalkingPlayerPos(Coordinate position ,int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (PlayerValues.PLAYER_LEFT, position,health);
        return myCordSprite;
    }

    public CoordinateSprite getRightStandingPlayerPos(Coordinate position ,int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (PlayerValues.PLAYER_STANDING_RIGHT, position ,health);
        return myCordSprite;

    }
    public CoordinateSprite getLeftStandingPlayerPos(Coordinate position ,int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (PlayerValues.PLAYER_STANDING_LEFT, position ,health);
        return myCordSprite;
    }

    public CoordinateSprite getBulletSprite(Coordinate position){
        CoordinateSprite myBullet = new CoordinateSprite (PlayerValues.BULLET, position,0);
        return myBullet;
    }
}
