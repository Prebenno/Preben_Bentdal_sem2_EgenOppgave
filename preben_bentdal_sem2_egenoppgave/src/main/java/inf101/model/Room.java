package inf101.model;

import inf101.grid.Grid;
import inf101.grid.OutOfBoundsException;

public class Room extends Grid<Pixel>{

   
       
    public Room(int rows, int columns, Pixel base_element) throws OutOfBoundsException {
        super(rows, columns, base_element);
       
    }
    
}
