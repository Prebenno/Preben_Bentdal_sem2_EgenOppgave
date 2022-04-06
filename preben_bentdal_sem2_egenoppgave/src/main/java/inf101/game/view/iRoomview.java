package inf101.game.view;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.grid.Coordinate;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;
import inf101.model.Sprite.CoordinateSprite;

public interface iRoomview {

    /** @return number of rows in grid */
    int getRows();

    /** @return number of columns in grid */
    int getColumns();

    Iterable<itemWithCoordinate<Pixel>> getPixels();

    Iterable<itemWithCoordinate<Pixel>> getSpritePixels();

    Iterable<itemWithCoordinate<Pixel>> getBulletPixels();

    Coordinate getCenter();

    int getPlayerHeight();

    int getPlayerWidth();

    CoordinateSprite getPlayerSprite();

    CoordinateSprite getBulletSprite();

    PlayerDirection getPlayerDirection();

    FootType getWalkingType();

    boolean isBulletGonnaShoot();



   

   
    


    
}
