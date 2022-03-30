package inf101.backround;

import java.awt.Color;
import inf101.grid.OutOfBoundsException;
import inf101.model.Pixel;
import inf101.model.Room;

public class Floor {

    public Room room;

    public Floor() throws OutOfBoundsException{
        this.room = new Room(200,200, new Pixel(Color.WHITE,false));

    }
    
}
