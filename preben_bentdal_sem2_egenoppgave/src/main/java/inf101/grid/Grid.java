package inf101.grid;

import java.util.ArrayList;
import java.util.List;

public class Grid<E> implements IGrid<E> {

    private List<ArrayList<E>> cells;
    private final int rows;
    private final int columns;

    public Grid(int rows, int columns, E base_element) throws OutOfBoundsException{
        if ((rows<=0) || (columns<=0)){
            throw new OutOfBoundsException("rows or cols is less than 0");
        }
        this.rows = rows;
        this.columns = columns;
        this.cells = new ArrayList <ArrayList<E>>(rows*columns); 
        for(int row = 0; row < rows ; row++){
            ArrayList<E> inner = new ArrayList<E>();
            cells.add(inner);
            for(int col = 0; col < columns ; col++){
                cells.get(row).add(base_element);
            }
        }
        
    }

    public Grid(int rows, int columns) throws OutOfBoundsException{
        this(rows,columns,null);
    }

    @Override
    public E getElement(Coordinate cord) {
        if (! coordinateOnBoard(cord)){
            throw new IndexOutOfBoundsException("coordinate not on board");
        }

        int row = cord.getRow();
        int column = cord.getColumn();
        return this.cells.get(row).get(column);
    }

    @Override
    public void setElement(Coordinate cord ,E element) {
        if (! coordinateOnBoard(cord)){
            throw new IndexOutOfBoundsException("coordinate not on board");
        }
        int row = cord.getRow();
        int column = cord.getColumn();
        this.cells.get(row).set(column, element);
    }

    @Override
    public int getRows() {
        return this.rows;
    }
    @Override
    public int getColumns() {
        return this.columns;
    }
    @Override
    public boolean coordinateOnBoard(Coordinate cord) {
        if ((cord.getColumn() >= 0) && (cord.getRow() >= 0)){
            return  (cord.getColumn() < this.getColumns()) && (cord.getRow() < this.getRows());
        }
        return false;
    }

    

    
    
}
