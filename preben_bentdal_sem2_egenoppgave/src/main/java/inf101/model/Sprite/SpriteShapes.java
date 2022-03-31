package inf101.model.Sprite;

import java.awt.Color;

import inf101.model.Pixel;

public class SpriteShapes {
    private Pixel pixels;
    private boolean[][] shape;

    static final SpriteShapes PLAYER_RIGHT = new SpriteShapes(new Pixel(Color.BLACK, true), new boolean[][] {
    {false,false,false,false,false,true,false,false,false,false,false},
    {false,false,false,false,true,true,true,false,false,false,false},
    {false,false,false,true,false,true,false,true,false,false,false},
    {false,false,false,true,true ,true,true ,true,false,false,false},
    {false,false,false,true,true ,true,true ,true,false,false,false},
    {false,false,false,false,true,true,true,false,false,false,false},
    {false,false,false,false,false,true,false,false,false,false,false},
    {false,false,false,false,false,true,false,false,true,true,true},
    {false,false,false,false,false,true,true,true,true,true,false,false},
    {false,false,false,false,false,true,false,false,false,false,false},
    {false,false,false,false,false,true,false,false,false,false,false},
    {false,false,false,false,false,true,false,false,false,false,false},
    {false,false,false,false,true ,true ,true,false,false,false,false},
    {false,false,false,true,true,false ,true,true,false,false,false},
    {false,false,false,true ,false,false,false,true,false,false,false},
    {false,false,false,true,false,false,false,true,false,false,false},
    {false,false,true,true ,false,false,false,true,true,false,false},
    {false,true,true,false,false,false,false,false,true,true,false},
    });
    static final SpriteShapes PLAYER_LEFT = new SpriteShapes(new Pixel(Color.BLACK, true), new boolean[][] {
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,true,true,true,false,false,false,false},
        {false,false,false,true,false,true,false,true,false,false,false},
        {false,false,false,true,true ,true,true ,true,false,false,false},
        {false,false,false,true,true ,true,true ,true,false,false,false},
        {false,false,false,false,true,true,true,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {true,true,true,false,false,true,false,false,false,false,false},
        {false,false,true,true,true,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,true ,true ,true,false,false,false,false},
        {false,false,false,true,true,false ,true,true,false,false,false},
        {false,false,false,true ,false,false,false,true,false,false,false},
        {false,false,false,true,false,false,false,true,false,false,false},
        {false,false,true,true ,false,false,false,true,true,false,false},
        {false,true,true,false,false,false,false,false,true,true,false},
        });
    static final SpriteShapes PLAYER_STANDING_RIGHT = new SpriteShapes(new Pixel(Color.BLACK, true), new boolean[][] {
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,true,true,true,false,false,false,false},
        {false,false,false,true,false,true,false,true,false,false,false},
        {false,false,false,true,true ,true,true ,true,false,false,false},
        {false,false,false,true,true ,true,true ,true,false,false,false},
        {false,false,false,false,true,true,true,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,true,true,true},
        {false,false,false,false,false,true,true,true,true,true,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        });
    static final SpriteShapes PLAYER_STANDING_LEFT = new SpriteShapes(new Pixel(Color.BLACK, true), new boolean[][] {
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,true,true,true,false,false,false,false},
        {false,false,false,true,false,true,false,true,false,false,false},
        {false,false,false,true,true ,true,true ,true,false,false,false},
        {false,false,false,true,true ,true,true ,true,false,false,false},
        {false,false,false,false,true,true,true,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {true,true,true,false,false,true,false,false,false,false,false},
        {false,false,true,true,true,true,false,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        {false,false,false,false,false,true,false,false,false,false,false},
        });
        
    

    private SpriteShapes(Pixel pixels, boolean[][] shape ){
        this.pixels = pixels;
        this.shape = shape;
        
    }

    public boolean[][] getShape() {
        return this.shape;
    }

    public Pixel getPixels(){
        return this.pixels;
    }
    public int getWidth(){
        for (boolean[] bs : shape) {
            int width = bs.length;
            return width;
        }
        return 0;
        


    }
    public int getHeight(){
        return shape.length;
    }

}