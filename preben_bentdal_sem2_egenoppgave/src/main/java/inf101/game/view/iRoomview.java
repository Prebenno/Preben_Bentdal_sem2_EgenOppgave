package inf101.game.view;

import inf101.grid.Coordinate;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;

public interface iRoomview {

    /** @return number of rows in grid */
   int getRows();

   /** @return number of columns in grid */
   int getColumns();

   Iterable<itemWithCoordinate<Pixel>> getPixels();

   Iterable<itemWithCoordinate<Pixel>> getSpritePixels();

   Coordinate getCenter();

   int getHeight();

   int getWidth();
    


    
}
