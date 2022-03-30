package inf101.model.Sprite;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate center;
    

    public void setSenterColumn(){
        this.center = new Coordinate(0,0);

    }

    public CoordinateSprite getPlayerSprite(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteShapes.PLAYER, center);
        return myCordSprite;

    }
}
