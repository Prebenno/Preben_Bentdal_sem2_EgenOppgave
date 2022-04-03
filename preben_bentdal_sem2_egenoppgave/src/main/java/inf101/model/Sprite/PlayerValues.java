package inf101.model.Sprite;

import java.awt.Color;

import inf101.model.Pixel;

public class PlayerValues {
    private Pixel pixels;
    private boolean[][] shape;
    private final int speed;
    private int acceleration;

    static final PlayerValues PLAYER_RIGHT = new PlayerValues(new Pixel(Color.BLACK, true), new boolean[][] {
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
    }
    ,1,1);
    static final PlayerValues PLAYER_LEFT = new PlayerValues(new Pixel(Color.BLACK, true), new boolean[][] {
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
        }
        ,1,1);
    static final PlayerValues PLAYER_STANDING_RIGHT = new PlayerValues(new Pixel(Color.BLACK, true), new boolean[][] {
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
        }
        ,1,1);
    static final PlayerValues PLAYER_STANDING_LEFT = new PlayerValues(new Pixel(Color.BLACK, true), new boolean[][] {
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
        }
        ,1,1);
        
    

    private PlayerValues(Pixel pixels, boolean[][] shape,int speed,int acceleration ){
        this.pixels = pixels;
        this.shape = shape;
        this.speed = speed;
        this.acceleration = acceleration;
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

    public int getSpeed(){
        return this.speed;
    }
    public int getAcceleration(){
        return this.acceleration;
    }
    public void setAcceleration(){
        System.out.println("Accerelation = " + this.acceleration);
    
        if (this.acceleration < 10){
            this.acceleration++;
        }
    }

    public void reset(){
        this.acceleration = 1;
    }
}