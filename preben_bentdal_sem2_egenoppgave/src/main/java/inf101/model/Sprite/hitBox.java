package inf101.model.Sprite;

import java.util.Objects;

public class hitBox {
    private boolean[][] values;

    public hitBox (boolean[][] values){
        this.values = values;
    
    }

    static final hitBox PLAYER_HITBOX = new hitBox(new boolean[][]{
    {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
    {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},

    });

    static final hitBox TRAPDOOR = new hitBox(new boolean[][]{
        {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true},
        {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true},
    
        });


    static final hitBox BULLET = new hitBox(new boolean[][]{ 
    {false,false,false,true,false,false,false},
    {false,false,true,true,true,false,false},
    {false,true,true,true,true,true,false},
    {false,true,true,true,true,true,false},
    {false,true,true,true,true,true,false},
    {false,true,true,true,true,true,false},
    {false,false,true,true,true,false,false},
    {false,false,false,true,false,false,false},
    });

   
    

    public boolean[][] getValues() {
        return this.values;
    }

    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof hitBox)) {
            return false;
        }
        hitBox hitBox = (hitBox) o;
        return Objects.equals(values, hitBox.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(values);
    }

    @Override
    public String toString() {
        return "{" +
            " values='" + getValues() + "'" +
            "}";
    }

                    
        
        
    

    

    
}
