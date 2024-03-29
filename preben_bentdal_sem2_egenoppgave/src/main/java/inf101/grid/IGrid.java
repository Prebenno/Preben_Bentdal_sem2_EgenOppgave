package inf101.grid;



public interface IGrid<E> extends Iterable<itemWithCoordinate<E>> {


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
     * Returns the number of columns in the table
    * 
    * @return The number of columns in the grid.
    */
    int getColumns();

    /**
     * checks if coordinate is on grid, this includes walls
     * @param cord coordinate to check
     * @return true/false
     */
    boolean coordinateOnGrid(Coordinate cord);

    /**
     * checks if coordinate is on board, this does not incude walls
     * @param cord coordinate to check
     * @return true/false
     */
    boolean coordinateOnFloor(Coordinate cord);

    boolean bulletNotInWall(Coordinate cord);

    
}
