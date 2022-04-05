package inf101.backround;

import java.awt.Color;
import inf101.grid.OutOfBoundsException;
import inf101.model.Pixel;
import inf101.model.Room;

public class Floor {

    public Room room;

    public Floor() throws OutOfBoundsException{
        Pixel floortile =new Pixel(new Color(51,51,51),false);
        Pixel stone1 = new Pixel(new Color(102,102,102),false);
        Pixel stone2 = new Pixel(new Color(153,153,153),false);
        Pixel stone3 = new Pixel(new Color(153,153,153),false);
        this.room = new Room(500,500, floortile,stone1,stone2,stone3);

    }
    public Floor(boolean wall) throws OutOfBoundsException{
        Pixel floortile =new Pixel(new Color(51,51,51),false);
       
        this.room = new Room(20,300, floortile);  
}
}
