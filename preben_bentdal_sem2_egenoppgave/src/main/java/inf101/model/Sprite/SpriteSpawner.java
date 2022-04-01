package inf101.model.Sprite;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate center;
    

    public void setSenterColumn(){
        this.center = new Coordinate(0,0); // player spawnpoint not on walls

    }
    public CoordinateSprite getStarterSprite(){
        return getRightWalkingPlayerPos(new Coordinate(50,50));
    }

    public CoordinateSprite getRightWalkingPlayerPos(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteShapes.PLAYER_RIGHT, position);
        return myCordSprite;

    }
    public CoordinateSprite getLeftWalkingPlayerPos(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteShapes.PLAYER_LEFT, position);
        return myCordSprite;
    }

    public CoordinateSprite getRightStandingPlayerPos(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteShapes.PLAYER_STANDING_RIGHT, position);
        return myCordSprite;

    }
    public CoordinateSprite getLeftStandingPlayerPos(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteShapes.PLAYER_STANDING_LEFT, position);
        return myCordSprite;
    }
}
