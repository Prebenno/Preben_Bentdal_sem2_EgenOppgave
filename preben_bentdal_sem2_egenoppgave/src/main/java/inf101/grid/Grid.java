package inf101.grid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {

    private List<ArrayList<E>> cells;
    private final int rows;
    private final int columns;

    public Grid(int rows, int columns, E base_element,E stoneColor1, E stoneColor2, E stoneColor3) throws OutOfBoundsException{
        if ((rows<=0) || (columns<=0)){
            throw new OutOfBoundsException("rows or cols is less than 0");
        }
        ArrayList<E> colors = CreateRandomColorList(base_element,stoneColor1, stoneColor2, stoneColor3,rows*columns);
        this.rows = rows;
        this.columns = columns;
        this.cells = new ArrayList <ArrayList<E>>(rows*columns);
        int color_index = 0; 
        for(int row = 0; row < rows ; row++){
            ArrayList<E> inner = new ArrayList<E>();
            cells.add(inner);

            for(int col = 0; col < columns ; col++){
                cells.get(row).add(colors.get(color_index));
                color_index++;
            }
        }
        
    }

    public ArrayList<E> CreateRandomColorList(E base_element,E stoneColor1, E stoneColor2, E stoneColor3,int size){
        ArrayList<E> numbers = new ArrayList<>(Arrays.asList(base_element,stoneColor1,stoneColor2,stoneColor3,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element,base_element));
        ArrayList<E> colors = new ArrayList<E>();
        for (int i = 0; i < size ; i++ ){
            int randomIndex = (int)(Math.random() * numbers.size());
            colors.add(numbers.get(randomIndex));
        }
        return colors;
    }
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
        this(rows,columns,null,null,null,null);
    }
    

    @Override
    public E getElement(Coordinate cord) {
        if (! coordinateOnGrid(cord)){
            throw new IndexOutOfBoundsException("coordinate not on board (wrong in get element)");
        }

        int row = cord.getRow();
        int column = cord.getColumn();
        return this.cells.get(row).get(column);
    }

    @Override
    public void setElement(Coordinate cord ,E element) {
        if (! coordinateOnGrid(cord)){
            throw new IndexOutOfBoundsException("coordinate not on board (wrong in set element)");
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
    public boolean coordinateOnGrid(Coordinate cord) {
        if ((cord.getColumn() >= 0) && (cord.getRow() >= 0)){
            return  (cord.getColumn() < this.getColumns()) && (cord.getRow() < this.getRows());
        }
        return false;
    }
    @Override
    public boolean coordinateOnFloor(Coordinate cord){
        if ((cord.getColumn() >= 25) && (cord.getRow() >= 40)){
            return  (cord.getColumn() < this.getColumns()-25) && (cord.getRow() < this.getRows()-70);
        }
        return false;
    }
    @Override
    public boolean bulletNotInWall(Coordinate cord){
        if ((cord.getColumn() >= 25) && (cord.getRow() >= 40)){
            return  (cord.getColumn() < this.getColumns()-25) && (cord.getRow() < this.getRows()-35);
        }
        return false;
    }
    

    @Override
    public Iterator<itemWithCoordinate<E>> iterator() { //Code gotten from https://stackoverflow.com/questions/5849154/can-we-write-our-own-iterator-in-java
        ArrayList<itemWithCoordinate<E>> IteratorList = new ArrayList<itemWithCoordinate<E>>();
        
        for (int row = 0; row < this.getRows(); row++) {
            for (int column = 0; column < this.getColumns(); column++) {
                Coordinate coordinate = new Coordinate(row,column);
                E elem = this.getElement(coordinate);
                itemWithCoordinate<E> itemWithCoordinate = new itemWithCoordinate<E>(coordinate,elem);
                IteratorList.add(itemWithCoordinate);
            }
        }
        return IteratorList.iterator();
    }

    

    
    
}
