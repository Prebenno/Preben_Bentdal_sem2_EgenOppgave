package inf101.model;

import inf101.backround.Floor;
import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.game.controller.IGameController;
import inf101.game.view.iRoomview;
import inf101.grid.Coordinate;
import inf101.grid.OutOfBoundsException;
import inf101.grid.itemWithCoordinate;
import inf101.model.Sprite.CoordinateSprite;
import inf101.model.Sprite.SpriteSpawner;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements iRoomview ,IGameController {
    Floor myfloor;
    Room myroom;
    CoordinateSprite PlayerSprite;
    private List<CoordinateSprite> bullets = new ArrayList<>();
    
    public SpriteSpawner spawner;
    public PlayerDirection direction;
    public FootType footType;

    public boolean bulletShoot = false;

    

    public GameModel() throws OutOfBoundsException {
        this.direction = PlayerDirection.RIGHT;
        this.footType = FootType.WALK;
        this.spawner = new SpriteSpawner();
        this.spawner.setSenterColumn();
        this.PlayerSprite = spawner.getStarterSprite();
        this.myfloor = new Floor();
        this.myroom = myfloor.room;
        
    }
    public GameModel(Boolean wall) throws OutOfBoundsException{
        this.direction = PlayerDirection.RIGHT;
        this.footType = FootType.WALK;
        this.spawner = new SpriteSpawner();
        this.spawner.setSenterColumn();
        this.PlayerSprite = spawner.getStarterSprite();
        this.myfloor = new Floor(true);
        this.myroom = myfloor.room;
    }
    @Override
    public int getRows() {
        return myroom.getRows();
    }

    @Override
    public int getColumns() {
        
        return myroom.getColumns();
    }
    @Override
    public Iterable<itemWithCoordinate<Pixel>> getPixels() {
        return myroom;
    }
    @Override
    public Iterable<itemWithCoordinate<Pixel>> getSpritePixels() {
        
        return PlayerSprite;
    }
    @Override
    public Iterable<itemWithCoordinate<Pixel>> getBulletPixels() {
        return bullets.get(0);
    }

    public Coordinate getCenter(){
        return spawner.center;
    }

    public int getPlayerWidth(){
        return PlayerSprite.getWidth();

    }
    public int getPlayerHeight(){
        return PlayerSprite.getHeight();

    }
    @Override
    public boolean moveObject(int deltaRow, int deltaColumn, CoordinateSprite object) {
        System.out.println("moved");
        for (itemWithCoordinate<Pixel> coordItem : object ) { //Checks if all coordinates is on grid
            int column= coordItem.getCoordinate().getColumn();
            int row = coordItem.getCoordinate().getRow();
            Coordinate testcord = new Coordinate(row + deltaRow, column + deltaColumn);
            if ((myroom.coordinateOnFloor(testcord))) {  // if coordinate is not on grid 
                if (object.equals(this.PlayerSprite)){
                    this.PlayerSprite = this.PlayerSprite.move(deltaRow, deltaColumn);   
                    } 
                else if (object.equals(this.bullets.get(0))){
                    this.bullets.add(0, this.bullets.get(0).move(deltaRow,deltaColumn));  
                }  
                return true;
            }   
        }
        System.out.println("yey");
        return false;
        
    }

    @Override
    public CoordinateSprite getPlayerSprite() {
        
        return this.PlayerSprite;
    }

    @Override
    public PlayerDirection getPlayerDirection() {
        
        return this.direction;
    }

    @Override
    public FootType getWalkingType() {
        return this.footType;
    }
    
    @Override
    public void changeWalkingDirection(PlayerDirection direction){
        this.direction = direction;
        switch(direction){
            case LEFT:
                this.PlayerSprite = spawner.getLeftWalkingPlayerPos(this.PlayerSprite.getCoordinate());
                break;
            default:
                this.PlayerSprite = spawner.getRightWalkingPlayerPos(this.PlayerSprite.getCoordinate());
                break;
        }
    }
    @Override
    public void changeFootType(FootType walking){
        this.footType = walking;
        switch(walking){
            case WALK:
                switch(this.direction){
                    case LEFT:
                        this.PlayerSprite = spawner.getLeftWalkingPlayerPos(this.PlayerSprite.getCoordinate());
                        break;
                    default:
                        this.PlayerSprite = spawner.getRightWalkingPlayerPos(this.PlayerSprite.getCoordinate());
                        break;
                }
                break;
            default:
                switch(this.direction){
                    case LEFT:
                        this.PlayerSprite = spawner.getLeftStandingPlayerPos(this.PlayerSprite.getCoordinate());
                        break;
                    default:
                        this.PlayerSprite = spawner.getRightStandingPlayerPos(this.PlayerSprite.getCoordinate());
                        break;
                }
                break;
        }

    }
    @Override
    public void resetAcceleration() {
        this.PlayerSprite.getEntity().reset();
    }
    //
    @Override
    public CoordinateSprite getFirstBullet() {
        return this.bullets.get(0);
        
    }
    @Override
    public CoordinateSprite getPlayer() {
        
        return this.PlayerSprite;
    }


    @Override
    public boolean isBulletGonnaShoot() {
        return (getFirstBullet() != null);
    }
    @Override
    public void loadBullet(boolean shot) {
        this.bullets.add(spawner.getBulletSprite(this.PlayerSprite.getCoordinate()));
        
    }
    @Override
    public boolean bulletInChaimber() {
        return isBulletGonnaShoot();
    }
    @Override
    public CoordinateSprite getBulletSprite() {
        if (this.bullets.size() >0){
            return this.bullets.get(0);
        }
        
        return null;
    }
    @Override
    public void bulletHit() {
        this.bullets.remove(0);
    }
    
    public List<CoordinateSprite> getAllBullets(){
        return this.bullets;

    }
    
}
