package inf101.model;

import inf101.grid.Grid;
import inf101.grid.OutOfBoundsException;

public class Room extends Grid<Pixel>{

    public Room(int rows, int columns, Pixel base_element,Pixel stone1, Pixel stone2, Pixel stone3) throws OutOfBoundsException {
        super(rows, columns, base_element,stone1,stone2,stone3);
    }
       
    public Room(int rows, int columns, Pixel base_element) throws OutOfBoundsException {
        super(rows, columns, base_element);
       
    }
    
}
