package inf101.model.Sprite;

import java.util.ArrayList;
import java.util.Iterator;

import inf101.grid.Coordinate;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;

public class CoordinateSprite implements Iterable<itemWithCoordinate<Pixel>>{
    private SpriteShapes entity;
    private Coordinate coordinate;


    public CoordinateSprite(SpriteShapes entity, Coordinate coordinate) {
        this.entity = entity;
        this.coordinate = coordinate;

    }
    public SpriteShapes getEntity(){
        return this.entity;
    }
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    public int getWidth(){
        return entity.getWidth();

    }
    public int getHeight(){
        return entity.getHeight();
    }


    @Override
    public Iterator<itemWithCoordinate<Pixel>> iterator() {
        ArrayList<itemWithCoordinate<Pixel>> coordinates = new ArrayList<itemWithCoordinate<Pixel>>();
        boolean[][] boolist = this.entity.getShape();
        int x_pos = getCoordinate().getRow();
        int y_pos = getCoordinate().getColumn();
        
        for (int row = 0; row < this.getHeight(); row++) {
            for (int column = 0; column < this.getWidth(); column++) {
                if(boolist[row][column]){
                    coordinates.add(new itemWithCoordinate<Pixel>(new Coordinate(row+x_pos, column+y_pos), this.getEntity().getPixels() ));
                }
            }
            
        }
        return coordinates.iterator();
    }
    public CoordinateSprite move(int deltaRow, int deltaCol){
        int oldRow = getCoordinate().getRow();
        int oldCol = getCoordinate().getColumn();
        CoordinateSprite copy = new CoordinateSprite(this.entity, new Coordinate(oldRow+deltaRow, oldCol+deltaCol));
        return copy;

    }
}
