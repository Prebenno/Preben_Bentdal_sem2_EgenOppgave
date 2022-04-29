package inf101.model;

import inf101.backround.Floor;
import inf101.game.States.FootType;
import inf101.game.States.GameState;
import inf101.game.States.Direction;
import inf101.game.controller.IGameController;
import inf101.game.view.iRoomview;
import inf101.grid.Coordinate;
import inf101.grid.OutOfBoundsException;
import inf101.grid.itemWithCoordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;
import inf101.model.Sprite.SpriteValues;
import inf101.model.Sprite.SpriteSpawner;


import java.util.ArrayList;
import java.util.List;

import java.util.stream.StreamSupport;

public class GameModel implements iRoomview ,IGameController {
    private Floor myfloor;
    private Room myroom;
    private CoordinateSprite playerSprite;
    private CoordinateSprite trapdoor;
    private GameState gameState;

    private int PLAYER_TIME_BETWEEN_SHOTS = 500;
    
    private int SCOREMULTIPLIER = 1;
    private int PlayerBulletDamage = 10;
    private int MonsterBulletDamage = 50;
    public int floorNum = 1;
    public boolean finished = false;
    int counter = 0;
    private int score;

    private List<CoordinateSprite> powerups = new ArrayList<CoordinateSprite>();
    private List<CoordinateSprite> enemySprites = new ArrayList<CoordinateSprite>();
    private List<Bullet> bullets = new ArrayList<>();
    public SpriteSpawner spawner;

    public GameModel() throws OutOfBoundsException {
        this.gameState = GameState.ACTIVE_GAME;
        this.spawner = new SpriteSpawner();
        this.spawner.setSenterColumn();
        this.playerSprite = spawner.getPlayer();
        this.enemySprites = spawner.getEnemyWave();
        this.myfloor = new Floor();
        this.myroom = myfloor.room;   
    }
    @Override
    public void activatePowerUps(){
    this.powerups.add(spawner.getAttackIncrease());
    this.powerups.add(spawner.getAttackSpeedIncrease());
    this.powerups.add(spawner.getHealth());
    this.powerups.add(spawner.getScoreIncrease());
    }
    @Override
    public List<CoordinateSprite> getPowerUps(){
        return this.powerups;
    }
    @Override 
    public void changeGameState(GameState newState){
        this.gameState = newState;
    }
    @Override 
    public GameState getGameState(){
        return this.gameState;
    }
    @Override 
    public int getTimeBetweenShots(){
        return this.PLAYER_TIME_BETWEEN_SHOTS;
    }
    public int getPlayerBulletDamage(){ //for testing
        return this.PlayerBulletDamage;
    }
    public int getScoreMultiplier(){ //for testing
        return this.SCOREMULTIPLIER;
    }
    public void changeTimeBetweenShots(int newTime){
        this.PLAYER_TIME_BETWEEN_SHOTS = newTime;
    }
    @Override 
    public int getScore(){
        return this.score;
    }
    @Override 
    public int getFloorNum(){
        return this.floorNum;
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
    public CoordinateSprite getPlayerSprite() {
        
        return this.playerSprite;
    }

    

    @Override
    public CoordinateSprite getTrapDoor() {
        return this.trapdoor;
    }
    @Override
    public List<CoordinateSprite> getEnemySprites() {
        return this.enemySprites;
        
    }
    @Override
    public boolean enemyExists() {
        return ((this.enemySprites.size() > 0)) && (!(isAllNulls(this.enemySprites)));   
    }
    
   
    public Coordinate getCenter(){
        return spawner.startPos;
    }

    /**
     * This function takes a list of bullets and sets the list of bullets in the game to that list.
     * Used for testing
     * @param newBullets The new list of bullets to replace the old one.
     */
    public void changeBullets(List<Bullet> newBullets) {
        this.bullets = newBullets;
        
    }
    
    @Override
    public <E> Object moveObject(int deltaRow, int deltaColumn, E object) {
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
                        Bullet myNewBullet = new Bullet(bullet.getXspeed(), bullet.getYspeed(), bullet.getShape().move(deltaRow, deltaColumn),bullet.isPlayerBullet(),bullet.getDamage());
                        return  myNewBullet;}
                    } 
            }   
        return null;
    }  
    @Override
    public void loadBullet(boolean shot,int Xspeed, int Yspeed, Coordinate startCoordinate) {
        int damage = PlayerBulletDamage;
        if (!shot){
            damage = MonsterBulletDamage;
        }
        this.bullets.add(new Bullet(Xspeed,Yspeed,spawner.getBulletSprite(startCoordinate),shot,damage));    
    }
    
    @Override
    public List<Bullet> getAllBullets() {
        
        return this.bullets;
    }
    @Override
    public boolean isBulletGonnaShoot() {
        return (bullets.size() > 0);
    }

    
    /**
     * This function resets the bullets array to an empty array, used for testing
     */
    public void resetBullet() {
        List<Bullet> test = new ArrayList<Bullet>();
        this.bullets = test;
        
    }
    
    @Override
    public CoordinateSprite damageObject(CoordinateSprite damageObject, CoordinateSprite reciever) {
            if(!reciever.damage(damageObject.getDamage())){ //damaging object and checking if reciever is dead
                this.score+= (SCOREMULTIPLIER*this.floorNum);
                return null; // of object is dead   
            }
        return reciever; 
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
    /**
     * If the bullet is within the rectangle, return true
     * 
     * @param top_right The top right coordinate of the rectangle
     * @param bottom_left The bottom left coordinate of the rectangle
     * @param point The coordinate of the bullet
     * @return The method is returning a boolean value.
     */
    private boolean bulletInRectangle(Coordinate top_right, Coordinate bottom_left,Coordinate point){
        if (point.getRow() > top_right.getRow()-10 && point.getRow() < bottom_left.getRow()-5 &&
       point.getColumn() > top_right.getColumn()-10 && point.getColumn() < bottom_left.getColumn()-5)
           return true;

       return false;
    }
    /**
     * source:
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
    public void monsterStep() {
        if (this.playerSprite != null){
            List<CoordinateSprite> enemies = new ArrayList<CoordinateSprite>();
            for (CoordinateSprite enemy : this.enemySprites) {
                CoordinateSprite newEnemy = enemy.copy(); //to prevent modificationerror
                monsterShoot(newEnemy);
                newEnemy = walkingDirectionHelper(enemy);
                newEnemy = movementHelper(newEnemy);
                enemies.add(newEnemy);       
            }
            this.enemySprites =  enemies;   
        }
        
    }   

    public void monsterShoot(CoordinateSprite sprite) {
        if(sprite.getEntity().equals(SpriteValues.SHOOTER)){
            int movementCount = sprite.getEntity().getTotalMovements();
            sprite.getEntity().addOneStep();  
            if (movementCount % 100 == 0){
                Coordinate bulletDir = directiontoCoordinate(sprite);
                loadBullet(false, bulletDir.getRow(), bulletDir.getColumn(),sprite.getCoordinate());
            }
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
    public CoordinateSprite movementHelper(CoordinateSprite enemy){
        Coordinate newCord = pathToEnemy(enemy);
        int newRow = newCord.getRow();
        int newColumn = newCord.getColumn();
        if (enemy.getEntity().equals(SpriteValues.SHOOTER)){ // to prevent shooter from running into player
            int row_diff = Math.abs(this.playerSprite.getCoordinate().getRow()-enemy.getCoordinate().getRow());
            int column_diff = Math.abs(this.playerSprite.getCoordinate().getColumn()-enemy.getCoordinate().getColumn());
            if (row_diff < 180 && (column_diff < 5)){ //i want the shooter to stay a little bit away from the player
                newRow = 0;
            }
            if (column_diff < 180 && (row_diff < 5)){
                newColumn = 0;
            }
        }
        CoordinateSprite newEnemy = enemy.move(newRow * enemy.getEntity().getSpeed(), newColumn * enemy.getEntity().getSpeed());
        if (SpriteCollision(this.playerSprite,this.playerSprite)){
            this.playerSprite = damageObject(newEnemy,this.playerSprite);
        }
        if (!SpriteCollision(newEnemy,enemy)){
            if (enemy.getEntity().getTotalMovements() % 5 == 0){  //To prevent to quick switches between sprites, causing the walking effect to look wierd
                FootType oldFootType = enemy.getFootType();
                newEnemy.changeFootType(oldFootType.next());
                    }
            enemy.getEntity().addOneStep();
            return newEnemy;}
        return enemy;
    }
           
    public Coordinate pathToEnemy(CoordinateSprite enemy){
        int playerRow = this.playerSprite.getCoordinate().getRow();
        int playerColumn = this.playerSprite.getCoordinate().getColumn();
        int enemyRow = enemy.getCoordinate().getRow();
        int enemyColumn = enemy.getCoordinate().getColumn();
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
        return new Coordinate(newRow,newColumn);

    }
    
    @Override
    public boolean SpriteCollision(CoordinateSprite newPositionSprite, CoordinateSprite oldPositionSprite){
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
    public CoordinateSprite walkingDirectionHelper(CoordinateSprite enemy){
        int playerRow = this.playerSprite.getCoordinate().getRow();
        int playerColumn = this.playerSprite.getCoordinate().getColumn();
        int enemyRow = enemy.getCoordinate().getRow();
        int enemyColumn = enemy.getCoordinate().getColumn();

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
    public void activateTrapDoor() {
        if (this.enemySprites.size()== 0){
            this.trapdoor = spawner.getTrapDoor();

        }
    }

    @Override
    public void nextFloor() {
        this.spawner.setSenterColumn();
        this.enemySprites = spawner.getEnemyWave();
        this.trapdoor = null;
        this.finished = false;
        this.MonsterBulletDamage =   (int) (this.MonsterBulletDamage *1.1);
        if (this.floorNum % 10 == 0) {
            this.spawner.addEnemy();

        }
        try {
            this.myfloor = new Floor();
        } catch (OutOfBoundsException e) {
            //wont happen
            e.printStackTrace();
        }
        this.myroom = myfloor.room;   
        floorNum++;
    }
    @Override
    public void moveAllBullets() {
        List<Bullet> newBullets = new ArrayList<Bullet>();
        for (Bullet bullet : this.bullets) {// first moving bullets
            Bullet movedBullet = (Bullet) moveObject(bullet.getXspeed(), bullet.getYspeed(), bullet);
            if (movedBullet != null){
                newBullets.add(movedBullet);}
        }
        this.bullets = newBullets;
    }

    @Override
    public void checkAndDamageBullets() {
        List<Bullet> deadBullets = new ArrayList<Bullet>();
        List<CoordinateSprite> newEnemies = new ArrayList<CoordinateSprite>();
        for (CoordinateSprite enemy : this.enemySprites){
            CoordinateSprite enemyCopy = enemy;
            for (Bullet bullet : this.bullets) {     
                if (bullet.isPlayerBullet()){              
                    if(simpleCollision(bullet, enemyCopy)){ 
                        enemyCopy = enemy.copy(); // to prevent ConcurrentModificationException
                        enemyCopy = damageObject(bullet.getShape(),enemyCopy);
                        deadBullets.add(bullet);
                        }
                    }
                if (!bullet.isPlayerBullet()){
                    if(simpleCollision(bullet,this.playerSprite)){
                        this.playerSprite = damageObject(bullet.getShape(),this.playerSprite);
                        deadBullets.add(bullet);
                        }
                    }
                }
            if (enemyCopy != null){
                newEnemies.add(enemyCopy);}
        }
    this.enemySprites = newEnemies;
    //Here enemy bullets are caclulated, bullets have a true function in them witch desides what object they will damage when
    for (Bullet bullet : deadBullets) {
        if (this.bullets.contains(bullet)){
            this.bullets.remove(bullet);
            }
        }
    }

    @Override
    public Coordinate directiontoCoordinate(CoordinateSprite sprite){
        switch (sprite.getDirection()){
            case DOWN:
                return new Coordinate(1,0);
            case LEFT:
                return new Coordinate(0,-1);
            case RIGHT:
                return new Coordinate(0,1);
            default:
                return new Coordinate(-1,0);      
        }
    }


    public void powerUpChecker(){
        
        boolean collected = false; // to prevent concurrentmodificationerror
        for (CoordinateSprite powerUp : this.powerups) {
            if (simpleCollision(this.playerSprite,powerUp)){
                collected = true;
                if(powerUp.getEntity().equals( SpriteValues.HEALTH)){
                    this.playerSprite.healthPowerup();
                    this.playerSprite.heal();
                }
                else if(powerUp.getEntity().equals(SpriteValues.ATTACKSPEED) ){
                    this.PLAYER_TIME_BETWEEN_SHOTS = (int) (this.PLAYER_TIME_BETWEEN_SHOTS * 0.8); 
                }
                else if(powerUp.getEntity().equals(SpriteValues.DAMAGEUP)){
                    this.PlayerBulletDamage = this.PlayerBulletDamage * 2;
                }
                else{
                    this.SCOREMULTIPLIER++;
                }      
            }
        }
        if(collected){
            this.powerups.clear();
            }

        
    }
}