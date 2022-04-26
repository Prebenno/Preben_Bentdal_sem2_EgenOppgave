package inf101.model;

import inf101.backround.Floor;
import inf101.game.States.FootType;
import inf101.game.States.Direction;
import inf101.game.controller.IGameController;
import inf101.game.view.iRoomview;
import inf101.grid.Coordinate;
import inf101.grid.OutOfBoundsException;
import inf101.grid.itemWithCoordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;
import inf101.model.Sprite.SpriteSpawner;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

public class GameModel implements iRoomview ,IGameController {
    private Floor myfloor;
    private Room myroom;
    private CoordinateSprite playerSprite;
    private CoordinateSprite trapdoor;

    public int score = 0;
    public boolean finished = false;

    private List<CoordinateSprite> enemySprites = new ArrayList();
    private List<Bullet> bullets = new ArrayList<>();

    private int BULLETDAMAGE = 10;
    
    private SpriteSpawner spawner;

    private boolean bulletShoot = false;

    public GameModel() throws OutOfBoundsException {
        
        this.spawner = new SpriteSpawner();
        this.spawner.setSenterColumn();
        this.playerSprite = spawner.getStarterSprite();
        this.enemySprites.add(spawner.getStarterSprite2()); 
        this.enemySprites.add(spawner.getStarterSprite3()); 
        this.enemySprites.add(spawner.getStarterSprite4()); 
        this.trapdoor = spawner.getTrapDoor();
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
    public Direction getDirection() {
        
        return this.playerSprite.getDirection();
    }

    @Override
    public FootType getWalkingType(CoordinateSprite object) {
        return object.getFootType();
    }
    
    @Override
    public void changeWalkingDirection(Direction direction, CoordinateSprite object){
        if (object != null){
            if (object.equals(this.playerSprite)){
                this.playerSprite.changeDirection(direction);
                
                }
            for (CoordinateSprite enemy : this.enemySprites) {
                if (object.equals(enemy)){
                    enemy.changeDirection(direction);
                    
                    }
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
            for (CoordinateSprite enemy : this.enemySprites) {
                if (object.equals(enemy)){
                    enemy.changeFootType(newFootType);
                }
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
    public CoordinateSprite damageObject(CoordinateSprite damageObject, CoordinateSprite reciever) {
            if(!reciever.damage(BULLETDAMAGE)){
                return null; // of object is dead   
            }
        return reciever; 
    }

    @Override
    public boolean didDamage(CoordinateSprite spriteOne, CoordinateSprite spriteTwo){
        if (spriteOne.getHealth() != spriteTwo.getHealth()){
            return true;
        }
        return false;

    }

    

    public <E> void killObject(E object){
        CoordinateSprite killedEnemy = null;
        if (this.enemySprites.contains(object)){
            for (CoordinateSprite enemy : this.enemySprites) {
                if (object.equals(enemy)){
                    killedEnemy = enemy;
                    }
                }        
            }
        else if (object.equals(this.playerSprite)){
            this.playerSprite = null;
        }
        this.enemySprites.remove(killedEnemy);
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
         if (point.getRow() > top_right.getRow() && point.getRow() < bottom_left.getRow() &&
        point.getColumn() > top_right.getColumn() && point.getColumn() < bottom_left.getColumn())
            return true;

        return false;
    }
    
    @Override
    public boolean enemyExists() {
    
        return ((this.enemySprites.size() > 0)) && (!(isAllNulls(this.enemySprites)));
        
    }

    
    /**
     * https://stackoverflow.com/questions/6243414/ways-to-check-if-an-arraylist-contains-only-null-values
     * If all the elements in the array are null, return true, otherwise return false.
     * 
     * @param array The array to check
     * @return A stream of the array with all nulls.
     */
    public static boolean isAllNulls(Iterable<?> array) {
        return StreamSupport.stream(array.spliterator(), true).allMatch(o -> o == null);
    }

    @Override
    public List<CoordinateSprite> monsterStep() {
        List<CoordinateSprite> enemies = new ArrayList<CoordinateSprite>();
        for (CoordinateSprite enemy : this.enemySprites) {
            CoordinateSprite newEnemy = enemy.copy(); //to prevent modificationerror
            Coordinate playerCoordinate = this.playerSprite.getCoordinate();
            Coordinate enemyCoordinate = newEnemy.getCoordinate();
            int playerColumn = playerCoordinate.getColumn();
            int playerRow = playerCoordinate.getRow();
            int enemyColumn = enemyCoordinate.getColumn();
            int enemyRow = enemyCoordinate.getRow();
            this.playerSprite = damageObject(newEnemy,this.playerSprite);
            if (this.playerSprite != null ){
                newEnemy = walkingDirectionHelper(playerRow, enemyRow, playerColumn,enemyColumn,enemy);
                newEnemy = movementHelper(playerRow, enemyRow, playerColumn,enemyColumn,enemy);
            }
            else{
                newEnemy.switchdamage();
            }
            enemies.add(newEnemy);       
        }
        return enemies;   
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
    public CoordinateSprite movementHelper(int playerRow, int enemyRow, int playerColumn, int enemyColumn,CoordinateSprite enemy){
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
        CoordinateSprite newEnemy = enemy.move(newRow, newColumn);
        if (!newCollision(newEnemy,enemy)){
            return newEnemy; }
        return enemy;
    }
   
     
    
    
    @Override
    public boolean newCollision(CoordinateSprite newPositionSprite, CoordinateSprite oldPositionSprite){
        for (itemWithCoordinate<Pixel> itemWithCoordinate : newPositionSprite) {
            Coordinate newPosCoordinate = itemWithCoordinate.getCoordinate();
            for (CoordinateSprite cordSprite : this.enemySprites) {
                if (!cordSprite.equals(oldPositionSprite)){
                    if(simpleCollision(newPosCoordinate,cordSprite)){
                        return true;
                    }
                }
            }
            if (!oldPositionSprite.equals(this.playerSprite)){
                if(simpleCollision(newPosCoordinate,this.playerSprite)){
                    return true;
                }
            }       
        }
        return false;
    }
     
    

    @Override
    public <E> boolean simpleCollision (E Object,CoordinateSprite victim){
        Coordinate attackerCords = null;
        boolean bullet = false;
        if (Object instanceof Bullet){
            Bullet attacker = (Bullet)Object;
            attackerCords = attacker.getShape().getCoordinate();
            bullet = true;

        }
        else if (Object instanceof CoordinateSprite){
            CoordinateSprite attacker = (CoordinateSprite)Object;
            attackerCords = attacker.getCoordinate(); 

        }
        else if (Object instanceof Coordinate){
            attackerCords = ((Coordinate)Object);
        }
        if (Object != null && victim !=null && attackerCords != null){
            int enemyHeight = victim.getHeight();
            int enemyWidth = victim.getWidth();
            int enemy_y = victim.getCoordinate().getColumn();
            int enemy_x = victim.getCoordinate().getRow();
            Coordinate top_right_enemy = victim.getCoordinate();
            Coordinate bottom_right_enemy = new Coordinate(enemy_x+enemyHeight, enemy_y + enemyWidth);
            if (bullet){
                if (bulletInRectangle(top_right_enemy, bottom_right_enemy,attackerCords)){
                    return true;
                }
            }
            else if(pointInRectangle(top_right_enemy, bottom_right_enemy,attackerCords)){
                return true;
            }
        }
        return false; 
    }
    private boolean bulletInRectangle(Coordinate top_right, Coordinate bottom_left,Coordinate point){
        if (point.getRow() > top_right.getRow()-10 && point.getRow() < bottom_left.getRow()-5 &&
       point.getColumn() > top_right.getColumn()-10 && point.getColumn() < bottom_left.getColumn()-5)
           return true;

       return false;
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
    public CoordinateSprite walkingDirectionHelper(int playerRow, int enemyRow, int playerColumn, int enemyColumn,CoordinateSprite enemy){
        
        double vecX = playerRow-enemyRow;
        double vecY = playerColumn-enemyColumn;
        double angle = Math.toDegrees(Math.atan(vecY /vecX));
        
        double trueAngle = (vecX > 0 ? angle : 180 + angle);
        if (-45 < trueAngle && trueAngle < 45){
            enemy.changeDirection(Direction.DOWN);
        }
        else if (135 <= trueAngle && trueAngle <= 225){
            enemy.changeDirection(Direction.UP);
        }
        else if ((45 < trueAngle && trueAngle < 135 && trueAngle != 90) || trueAngle == 270){
            enemy.changeDirection(Direction.RIGHT);
        }
        else{
            enemy.changeDirection(Direction.LEFT);
        }
        return enemy;
    }

    @Override
    public List<CoordinateSprite> getEnemies() {
        return this.enemySprites;
        
        
        
    }
    @Override
    public List<CoordinateSprite> getEnemySprite() {
       
            return this.enemySprites;
        
        
    }

    @Override
    public void changeEnemies(List<CoordinateSprite> newEnemies) {
        this.enemySprites = newEnemies;
        
    }

    @Override 
        public List<Bullet> getBulletCopy(){
        List<Bullet> mybullets = new ArrayList<Bullet>();
        for (Bullet bullet : getAllBullets()) {
            mybullets.add(bullet);
            
        }
        return mybullets;
    }

    @Override
    public CoordinateSprite getTrapDoor() {
        return this.trapdoor;
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

