package inf101.grid;

public interface IGrid<E> {


    /**
     * gets element from a coordinate
     * @param cord the coordinate of the element 
     * @return element in coordinate
     */
    E getElement(Coordinate cord);


    /**
     * sets element in coordinate
     * @param cord the coordinate to change element
     * @param element element to set in coordinate
     */
    void setElement(Coordinate cord,E element);

    /**
     * @return number of rows in grid
     */
    int getRows();

    /**
     * @return number of columns in grid
     */
    int getColumns();

    /**
     * @return true if coordinate is on board
     */
    boolean coordinateOnBoard(Coordinate cord);


    
}
