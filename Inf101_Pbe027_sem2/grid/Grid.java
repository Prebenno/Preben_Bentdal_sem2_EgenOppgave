package Inf101_Pbe027_sem2.grid;

import java.util.ArrayList;

public class Grid<E> implements IGrid<E> {

    ArrayList<ArrayList<E>> cells;
    public int rows;
    public final int columns;

    public Grid(int rows, int columns, E base_element) throws OutOfBoundsException{
        if ((rows<=0) || (columns<=0)){
            throw new OutOfBoundsException("rows or cols is less than 0");
        }
        this.rows = rows;
        this.columns = columns;
        
        for(int row = 0; row < rows ; row++){
            ArrayList<E> inner = new ArrayList<E>();
            cells.add(inner);
            for(int col = 0; col < columns ; col++){
                cells.get(row).add(base_element);
            }
        }
        
    }

    @Override
    public E getElement(Coordinate cord) {

        return null;
    }

    @Override
    public void setElement(E element) {
        // TODO Auto-generated method stub
        
    }

    
    
}
