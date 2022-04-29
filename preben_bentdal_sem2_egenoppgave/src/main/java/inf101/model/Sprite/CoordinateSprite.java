package inf101.model.Sprite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import inf101.game.States.FootType;
import inf101.game.States.Direction;
import inf101.grid.Coordinate;
import inf101.grid.itemWithCoordinate;
import inf101.model.Pixel;

import java.awt.Color;

public class CoordinateSprite implements Iterable<itemWithCoordinate<Pixel>>{
    private SpriteValues entity;
    private Coordinate coordinate;
    public int maxHealth =1000;
    private int HEALTH;
    private int DAMAGE = 10;
    private Direction direction;; 
    public FootType footType;
    private boolean damage = false;

    
    public CoordinateSprite(){ //used for testing
        this.entity = new SpriteValues(new Pixel(Color.BLUE,true), hitBox.PLAYER_HITBOX, 10,null,null,null,null,null,null,null,null);
        this.coordinate = new Coordinate(0,0);
        this.HEALTH = 100;
        this.direction = Direction.RIGHT;
        this.footType = FootType.STAND;
    }
    

    public CoordinateSprite(SpriteValues entity, Coordinate coordinate, int health) {  
        this.entity = entity;
        this.coordinate = coordinate;
        this.HEALTH = health;
        this.direction = Direction.RIGHT;
        this.footType = FootType.STAND;

    }
    public CoordinateSprite(SpriteValues entity, Coordinate coordinate, int health,int maxHealth,int damage, Direction direction, FootType footType) {  
        this.entity = entity;
        this.coordinate = coordinate;
        this.HEALTH = health;
        this.direction = direction;
        this.footType = footType;
        this.maxHealth = maxHealth;
        this.DAMAGE = damage;
        

    }
    public void healthPowerup(){
        this.maxHealth = (int) (this.maxHealth *1.2);
    }
    public void heal(){
        this.HEALTH = this.maxHealth;
    }
    public int getMaxHealth(){
        return this.maxHealth;
    }
    public int getHealth(){
        return this.HEALTH;
    }
    public int getDamage(){
        return this.DAMAGE;
    }
    public void setDamage(int newDamage){
        this.DAMAGE = newDamage;
    }
    
    public CoordinateSprite changeCoordinate(Coordinate newCord){
        this.coordinate = newCord;
        return this;
    }
    public SpriteValues getEntity(){
        return this.entity;
    }
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    public int getWidth(){
        return entity.getWidth();

    }
    public int getHeight(){
        return entity.getHeight();
    }
    public void changeFootType(FootType type){
        this.footType = type;
    }
    public FootType getFootType(){
        return this.footType;
    }

    public void changeDirection(Direction newDirection){
        this.direction = newDirection;

    }
    public Direction getDirection(){
        return this.direction;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CoordinateSprite)) {
            return false;
        }
        CoordinateSprite coordinateSprite = (CoordinateSprite) o;
        return Objects.equals(entity, coordinateSprite.entity) && Objects.equals(coordinate, coordinateSprite.coordinate) && maxHealth == coordinateSprite.maxHealth && HEALTH == coordinateSprite.HEALTH && DAMAGE == coordinateSprite.DAMAGE && Objects.equals(direction, coordinateSprite.direction) && Objects.equals(footType, coordinateSprite.footType) && damage == coordinateSprite.damage;
    }


    @Override
    public int hashCode() {
        return Objects.hash(entity, coordinate);
    }


    @Override
    public Iterator<itemWithCoordinate<Pixel>> iterator() {
        ArrayList<itemWithCoordinate<Pixel>> coordinates = new ArrayList<itemWithCoordinate<Pixel>>();
        boolean[][] boolist = this.entity.getShape();
        int x_pos = getCoordinate().getRow();
        int y_pos = getCoordinate().getColumn();
        
        for (int row = 0; row < this.getHeight(); row++) {
            for (int column = 0; column < this.getWidth(); column++) {
                if(boolist[row][column]){
                    coordinates.add(new itemWithCoordinate<Pixel>(new Coordinate(row+x_pos, column+y_pos), this.getEntity().getPixels() ));
                }
            }
            
        }
        return coordinates.iterator();
    }
    public CoordinateSprite move(int deltaRow, int deltaCol){
        int oldRow = getCoordinate().getRow();
        int oldCol = getCoordinate().getColumn();
        CoordinateSprite copy = new CoordinateSprite(this.entity, new Coordinate(oldRow+deltaRow, oldCol+deltaCol),this.HEALTH,this.maxHealth,this.DAMAGE,this.direction,this.footType);
        return copy;

    }
    /**
     * This function takes in an integer, adds it to the health of the player, and returns whether or
     * not the player is still alive.
     * 
     * @param damage The amount of damage to be dealt to the player.
     * @return The boolean value of the health being greater than 0.
     */
    public boolean damage(int damage){
        this.HEALTH-=damage;
        return this.HEALTH>0;
    }
    public CoordinateSprite copy(){   
        return new CoordinateSprite(this.entity, this.coordinate, this.HEALTH,this.maxHealth,this.DAMAGE,this.direction,this.footType);}
       
}
   

