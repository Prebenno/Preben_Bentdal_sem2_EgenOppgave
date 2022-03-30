package inf101.model;
import java.awt.Color;

public class Pixel {
    private Color color; // color of pixel
    private boolean sprite; //is sprite on pixel

    /**
     * Creates a new Pixel with the specified color and if sprite is on tile.
     * @param color color of pixel
     * @param sprite sprite on pixel?
     */
    public Pixel(Color color, boolean sprite){
        this.color = color;
        this.sprite = sprite;
    }
    
    /**
     * 
     * @return color of pixel
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * if sprite is on board
     * @return true if sprite is on board,false otherwise
     */
    public boolean getChar(){
        return this.sprite;
    
}
}
