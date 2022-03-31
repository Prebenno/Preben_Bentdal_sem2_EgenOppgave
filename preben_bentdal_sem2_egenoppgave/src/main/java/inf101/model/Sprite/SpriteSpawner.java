package inf101.model.Sprite;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate center;
    

    public void setSenterColumn(){
        this.center = new Coordinate(0,0);

    }
    public CoordinateSprite getStarterSprite(){
        return getRightWalkingPlayerPos(center);
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
