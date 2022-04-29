package inf101.grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GridTest {
    
        @Test
        void gridTestGetRowsAndCols() throws OutOfBoundsException {
        
            IGrid<Integer> grid = new Grid<Integer>(3,2);
            assertEquals(3, grid.getRows());
            assertEquals(2, grid.getColumns());
        }

        @Test
        void gridTrowsOutOfBoundsException() {
            try {
                IGrid<Integer> grid = new Grid<Integer>(3,0);
                grid.getRows();
                fail();
            } catch (OutOfBoundsException e) {
                //Test passed
            }try {
                IGrid<Integer> grid = new Grid<Integer>(0,3);
                grid.getRows();
                fail();
            } catch (OutOfBoundsException e) {
                //Test passed
            }
        }



        @Test
        void coordinateOnBoardTest() throws OutOfBoundsException{
            IGrid<Integer> grid = new Grid<Integer>(2,3);
            assertTrue(grid.coordinateOnGrid(new Coordinate(1,2)));
            assertTrue(grid.coordinateOnGrid(new Coordinate(1,0)));
            assertTrue(grid.coordinateOnGrid(new Coordinate(1,1)));

            assertFalse(grid.coordinateOnGrid(new Coordinate(3,3)));
            assertFalse(grid.coordinateOnGrid(new Coordinate(0,9)));
            assertFalse(grid.coordinateOnGrid(new Coordinate(9,0)));

            assertFalse(grid.coordinateOnGrid(new Coordinate(0,-1)));
            assertFalse(grid.coordinateOnGrid(new Coordinate(-1,0)));
            assertTrue(grid.coordinateOnGrid(new Coordinate(0,0)));
        }

        @Test
        void setAndGetTest() throws OutOfBoundsException{
            IGrid<Boolean> grid = new Grid<Boolean>(6,6,false);
            assertFalse(grid.getElement(new Coordinate(3,4)));
            assertFalse(grid.getElement(new Coordinate(1,3)));
            assertFalse(grid.getElement(new Coordinate(3,1)));

            grid.setElement(new Coordinate(3,4),true);
            grid.setElement(new Coordinate(1,3),true);
            grid.setElement(new Coordinate(3,1),true);

            assertTrue(grid.getElement(new Coordinate(3,4)));
            assertTrue(grid.getElement(new Coordinate(3,1)));
            assertTrue(grid.getElement(new Coordinate(1,3)));
            
        }
        @Test
        void iteratorTest() throws OutOfBoundsException{
            IGrid<Boolean> grid = new Grid<Boolean>(3,3,false);
            grid.setElement(new Coordinate(1, 0), true);
            grid.setElement(new Coordinate(2, 1), true);
            

            ArrayList<itemWithCoordinate<Boolean>> testList = new ArrayList<>();
            for (itemWithCoordinate<Boolean> itemWithCoordinate : grid) {
                testList.add(itemWithCoordinate);
            }
            assertTrue(testList.size() == 3*3);
            
            assertTrue(testList.contains(new itemWithCoordinate<Boolean>(new Coordinate(1, 0), (Boolean) true)));
            assertTrue(testList.contains(new itemWithCoordinate<Boolean>(new Coordinate(2, 1), true)));
            assertTrue(testList.contains(new itemWithCoordinate<Boolean>(new Coordinate(1, 2), false)));


        }
  
      
}

    
        
    
    
