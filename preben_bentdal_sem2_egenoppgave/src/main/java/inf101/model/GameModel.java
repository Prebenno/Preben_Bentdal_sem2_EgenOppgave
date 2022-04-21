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
    CoordinateSprite enemySprite;
    private List<Bullet> bullets = new ArrayList<>();

    private int BULLETDAMAGE = 10;
    
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
        this.enemySprite = spawner.getStarterSprite(); // for testing purposes
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
   
    public Coordinate getCenter(){
        return spawner.startPos;
    }

    public int getPlayerWidth(){
        return PlayerSprite.getWidth();

    }
    public int getPlayerHeight(){
        return PlayerSprite.getHeight();

    }

    @Override
    public Bullet moveBullet(int deltaRow, int deltaColumn, Bullet object) {
            for (itemWithCoordinate<Pixel> coordItem : object.getShape() ) { //Checks if all coordinates is on grid
                int column= coordItem.getCoordinate().getColumn();
                int row = coordItem.getCoordinate().getRow();
                Coordinate testcord = new Coordinate(row + deltaRow, column + deltaColumn );
                if ((myroom.bulletNotInWall(testcord))) {  // if coordinate is not on grid 
                    Bullet myNewBullet = new Bullet(object.getXspeed(), object.getYspeed(), object.getShape().move(deltaRow, deltaColumn));
                    return myNewBullet;
                }   
            }
            return null;    
        }
        
    
    
    @Override
    public boolean moveObject(int deltaRow, int deltaColumn, CoordinateSprite object) {
       
        for (itemWithCoordinate<Pixel> coordItem : object ) { //Checks if all coordinates is on grid
            int column= coordItem.getCoordinate().getColumn();
            int row = coordItem.getCoordinate().getRow();
            Coordinate testcord = new Coordinate(row + deltaRow, column + deltaColumn);
            if ((myroom.coordinateOnFloor(testcord))) {  // if coordinate is not on grid 
                if (object.equals(this.PlayerSprite)){
                    this.PlayerSprite = this.PlayerSprite.move(deltaRow, deltaColumn);   
                    } 
                return true;
            }   
        }
        return false;
        
    }
    
    @Override
    public Object MOVESOMETHING(int deltaRow, int deltaColumn, Object object) {return null;}
    

    /*
        CoordinateSprite moved_entity = null;
        Bullet bullet = null;
        if (object instanceof CoordinateSprite){
            moved_entity = this.PlayerSprite;
            }
        else if (object instanceof Bullet){
            bullet = (Bullet) object;
            moved_entity = bullet.getShape();
        }
        for (itemWithCoordinate<Pixel> coordItem : moved_entity ) { //Checks if all coordinates is on grid
            int column= coordItem.getCoordinate().getColumn();
            int row = coordItem.getCoordinate().getRow();
            Coordinate testcord = new Coordinate(row + deltaRow, column + deltaColumn);
              // if coordinate is not on grid 
                if (object instanceof CoordinateSprite){
                    if ((myroom.coordinateOnFloor(testcord))) {
                        this.PlayerSprite = this.PlayerSprite.move(deltaRow, deltaColumn);}  
                        return this.PlayerSprite; 
                    }
                else if (object instanceof Bullet){
                    if ((myroom.bulletNotInWall(testcord))) {
                        Bullet myNewBullet = new Bullet(bullet.getXspeed(), bullet.getYspeed(), bullet.getShape().move(deltaRow, deltaColumn));
                        return  myNewBullet;}
                    } 
            }   
        return null;
    }
        
        
    */

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
                this.PlayerSprite = spawner.getLeftWalkingPlayerPos(this.PlayerSprite.getCoordinate(),this.PlayerSprite.getHealth());
                break;
            default:
                this.PlayerSprite = spawner.getRightWalkingPlayerPos(this.PlayerSprite.getCoordinate(),this.PlayerSprite.getHealth());
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
                        this.PlayerSprite = spawner.getLeftWalkingPlayerPos(this.PlayerSprite.getCoordinate(),this.PlayerSprite.getHealth());
                        break;
                    default:
                        this.PlayerSprite = spawner.getRightWalkingPlayerPos(this.PlayerSprite.getCoordinate(),this.PlayerSprite.getHealth());
                        break;
                }
                break;
            default:
                switch(this.direction){
                    case LEFT:
                        this.PlayerSprite = spawner.getLeftStandingPlayerPos(this.PlayerSprite.getCoordinate(),this.PlayerSprite.getHealth());
                        break;
                    default:
                        this.PlayerSprite = spawner.getRightStandingPlayerPos(this.PlayerSprite.getCoordinate(),this.PlayerSprite.getHealth());
                        break;
                }
                break;
        }

    }
    @Override
    public void resetAcceleration() {
        this.PlayerSprite.getEntity().reset();
    }

    @Override
    public CoordinateSprite getPlayer() {
        
        return this.PlayerSprite;
    }

    @Override
    public void loadBullet(boolean shot,int Xspeed, int Yspeed) {
        this.bullets.add(new Bullet(Xspeed,Yspeed,spawner.getBulletSprite(this.PlayerSprite.getCoordinate())));
        
    }
    
    @Override
    public void bulletHit(Bullet bullet) {
        this.bullets.remove(bullet);
        
    }

    @Override
    public List<Bullet> getAllBullets() {
        
        return this.bullets;
    }
    
    @Override
    public boolean isBulletGonnaShoot() {
        return (bullets.size() > 0);
    }

    @Override
    public boolean bulletInChaimber() {
        return isBulletGonnaShoot();
    }
    @Override
    public void resetBullet() {
        List<Bullet> test = new ArrayList<Bullet>();
        this.bullets = test;
        
    }
    @Override
    public void changeBullets(List<Bullet> newBullets) {
        this.bullets = newBullets;
        
    }
    @Override
    public Iterable<itemWithCoordinate<Pixel>> getEnemyTestPixels() {
      
        return this.enemySprite;
    }
    @Override
    public boolean checkAndDamage(Bullet bullet) {
        if (this.enemySprite != null){
        
        int y_point = bullet.getShape().getCoordinate().getColumn();
        int x_point = bullet.getShape().getCoordinate().getRow();
        Coordinate bulletCoordinate = new Coordinate(x_point, y_point);

        int enemyHeight = this.enemySprite.getHeight();
        int enemyWidth = this.enemySprite.getWidth();
        int enemy_y = this.enemySprite.getCoordinate().getColumn();
        int enemy_x = this.enemySprite.getCoordinate().getRow();

        Coordinate top_right_enemy = this.enemySprite.getCoordinate();
        Coordinate bottom_right_enemy = new Coordinate(enemy_x+enemyHeight, enemy_y + enemyWidth);
        System.out.println(pointInRectangle(top_right_enemy, bottom_right_enemy,bulletCoordinate));
        if(pointInRectangle(top_right_enemy, bottom_right_enemy,bulletCoordinate)){
            if(!this.enemySprite.damage(BULLETDAMAGE)){
                this.enemySprite = null;
                
            }
            return true; // if bullet did damage 
            }
        }
        return false; // if bullet didnt do damage
        
    }

    /**
     * If the point is within the rectangle, return true. Otherwise, return false
     * 
     * @param top_right The top right coordinate of the rectangle
     * @param bottom_left The bottom left coordinate of the rectangle
     * @param point The point that we want to check if it's in the rectangle or not.
     * @return The method returns a boolean value.
     */
    private boolean pointInRectangle(Coordinate top_right, Coordinate bottom_left,Coordinate point){
        if (point.getRow() > top_right.getRow()-10 && point.getRow() < bottom_left.getRow()-5 &&
        point.getColumn() > top_right.getColumn()-10 && point.getColumn() < bottom_left.getColumn()-5)
            return true;

        return false;
    }
    @Override
    public boolean enemyExsists() {
        
        return (this.enemySprite != null);
    }

        }

