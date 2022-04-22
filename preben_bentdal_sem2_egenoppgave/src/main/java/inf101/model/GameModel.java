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


import java.util.ArrayList;
import java.util.List;

public class GameModel implements iRoomview ,IGameController {
    Floor myfloor;
    Room myroom;
    CoordinateSprite playerSprite;

    CoordinateSprite enemySprite;
    private List<Bullet> bullets = new ArrayList<>();

    private int BULLETDAMAGE = 10;
    
    public SpriteSpawner spawner;

    public boolean bulletShoot = false;

    public GameModel() throws OutOfBoundsException {
        
        this.spawner = new SpriteSpawner();
        this.spawner.setSenterColumn();
        this.playerSprite = spawner.getStarterSprite();
        this.enemySprite = spawner.getStarterSprite2(); // for testing purposes
        
        this.myfloor = new Floor();
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
        
        return playerSprite;
    }
   
    public Coordinate getCenter(){
        return spawner.startPos;
    }

    public int getPlayerWidth(){
        return playerSprite.getWidth();

    }
    public int getPlayerHeight(){
        return playerSprite.getHeight();

    }
    
    @Override
    public Object moveObject(int deltaRow, int deltaColumn, Object object) {
        CoordinateSprite moved_entity = null;
        Bullet bullet = null;
        if (object instanceof CoordinateSprite){
            moved_entity = this.playerSprite;
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
                        this.playerSprite = this.playerSprite.move(deltaRow, deltaColumn);}  
                        return this.playerSprite; 
                    }
                else if (object instanceof Bullet){
                    if ((myroom.bulletNotInWall(testcord))) {
                        Bullet myNewBullet = new Bullet(bullet.getXspeed(), bullet.getYspeed(), bullet.getShape().move(deltaRow, deltaColumn));
                        return  myNewBullet;}
                    } 
            }   
        return null;
    }
        
        
    

    @Override
    public CoordinateSprite getPlayerSprite() {
        
        return this.playerSprite;
    }

    @Override
    public PlayerDirection getDirection() {
        
        return this.playerSprite.getDirection();
    }

    @Override
    public FootType getWalkingType(CoordinateSprite object) {
        return object.getFootType();
    }
    
    @Override
    public void changeWalkingDirection(PlayerDirection direction, CoordinateSprite object){
        if (object != null){
            if (object.equals(this.playerSprite)){
                this.playerSprite.changeDirection(direction);
                }
            if (object.equals(this.enemySprite)){
                this.enemySprite.changeDirection(direction);
                }
            }
        }
    @Override
    public void changeFootType(FootType walking, CoordinateSprite object){
        if (object != null){
            FootType newFootType = object.getFootType();
            if (object.equals(this.playerSprite)){
                this.playerSprite.changeFootType(newFootType);

            }
            if (object.equals(this.enemySprite)){
                this.enemySprite.changeFootType(newFootType);
            }
        }
    }
        

    
    

    @Override
    public CoordinateSprite getPlayer() {
        
        return this.playerSprite;
    }

    @Override
    public void loadBullet(boolean shot,int Xspeed, int Yspeed) {
        this.bullets.add(new Bullet(Xspeed,Yspeed,spawner.getBulletSprite(this.playerSprite.getCoordinate())));
        
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
    public boolean checkAndDamage(CoordinateSprite damageObject, CoordinateSprite reciever) {
        if (damageObject != null && reciever !=null){
        
        int y_point = damageObject.getCoordinate().getColumn();
        int x_point = damageObject.getCoordinate().getRow();
        Coordinate bulletCoordinate = new Coordinate(x_point, y_point);

        int enemyHeight = reciever.getHeight();
        int enemyWidth = reciever.getWidth();
        int enemy_y = reciever.getCoordinate().getColumn();
        int enemy_x = reciever.getCoordinate().getRow();

        Coordinate top_right_enemy = reciever.getCoordinate();
        Coordinate bottom_right_enemy = new Coordinate(enemy_x+enemyHeight, enemy_y + enemyWidth);
        if(pointInRectangle(top_right_enemy, bottom_right_enemy,bulletCoordinate)){
            if(!reciever.damage(BULLETDAMAGE)){
                killObject(reciever); 
                
            }
            return true; // if bullet did damage 
            }
        }
        return false; // if bullet didnt do damage
        
    }

    public <E> void killObject(E object){
        if (object.equals(this.enemySprite)){
            this.enemySprite = null;
        }
        if (object.equals(this.playerSprite)){
            this.playerSprite = null;
        }
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

    @Override
    public void monsterStep() {
        Coordinate playerCoordinate = this.playerSprite.getCoordinate();
        Coordinate enemyCoordinate = this.enemySprite.getCoordinate();
        int playerColumn = playerCoordinate.getColumn();
        int playerRow = playerCoordinate.getRow();
        int enemyColumn = enemyCoordinate.getColumn();
        int enemyRow = enemyCoordinate.getRow();
        if (!checkAndDamage(this.enemySprite,this.playerSprite)){
            walkingDirectionHelper(playerRow, enemyRow, playerColumn,enemyColumn);
            movementHelper(playerRow, enemyRow, playerColumn,enemyColumn);
        }
        else{
            this.enemySprite.switchdamage();
        }
    
        
        
        
    }

    

    
    /**
     * If the player is to the right of the enemy, move the enemy to the right. If the player is to the
     * left of the enemy, move the enemy to the left. If the player is above the enemy, move the enemy
     * up. If the player is below the enemy, move the enemy down
     * 
     * @param playerRow the row of the player
     * @param enemyRow the row of the enemy
     * @param playerColumn the column of the player
     * @param enemyColumn the column of the enemy
     */
    public void movementHelper(int playerRow, int enemyRow, int playerColumn, int enemyColumn){
        int newRow = 0;
        int newColumn = 0;
        if (playerRow > enemyRow +1){
            newRow = 1;
        }
        else if (playerRow < enemyRow){
            newRow = -1;
        }
        if (playerColumn > enemyColumn+1){
            newColumn = 1;
        }
        else if (playerColumn < enemyColumn){
            newColumn = -1;
        }
        this.enemySprite = this.enemySprite.move(newRow, newColumn);
    }

    
    /**
     * //this idea was gotten from
     * //https://stackoverflow.com/questions/30168294/how-to-make-an-enemy-shoot-in-the-players-direction
     * i had to separate rotation from movement to prevent glitching at 45-135-225-(-90) angles
     * It takes the coordinates of the player and the enemy and calculates the angle between them. It
     * then uses that angle to determine which direction the enemy should be facing
     * 
     * @param playerRow the row of the player
     * @param enemyRow the row of the enemy
     * @param playerColumn the column of the player
     * @param enemyColumn the column of the enemy
     */
    public void walkingDirectionHelper(int playerRow, int enemyRow, int playerColumn, int enemyColumn){
        
        double vecX = playerRow-enemyRow;
        double vecY = playerColumn-enemyColumn;
        double angle = Math.toDegrees(Math.atan(vecY /vecX));
        
        double trueAngle = (vecX > 0 ? angle : 180 + angle);
        if (-45 < trueAngle && trueAngle < 45){
            changeWalkingDirection(PlayerDirection.DOWN,this.enemySprite);
        }
        else if (135 <= trueAngle && trueAngle <= 225){
            changeWalkingDirection(PlayerDirection.UP,this.enemySprite);
        }
        else if ((45 < trueAngle && trueAngle < 135 && trueAngle != 90) || trueAngle == 270){
            changeWalkingDirection(PlayerDirection.RIGHT,this.enemySprite);
        }
        else{
            changeWalkingDirection(PlayerDirection.LEFT,this.enemySprite);
        }
    }

    @Override
    public CoordinateSprite getEnemy() {
        
        return this.enemySprite;
    }
    
    @Override
    public CoordinateSprite getEnemySprite() {
        return this.enemySprite;
      
    }










    /*
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
    
    
    public boolean checkAndDamage2(Bullet bullet) {
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
        
        if(pointInRectangle(top_right_enemy, bottom_right_enemy,bulletCoordinate)){
            if(!this.enemySprite.damage(BULLETDAMAGE)){
                this.enemySprite = null;
                
            }
            return true; // if bullet did damage 
            }
        }
        return false; // if bullet didnt do damage
        
    }

    */
}

