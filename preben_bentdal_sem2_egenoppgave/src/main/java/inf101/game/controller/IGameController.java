package inf101.game.controller;

import java.util.List;

import inf101.game.States.FootType;
import inf101.game.States.GameState;
import inf101.game.States.Direction;
import inf101.grid.Coordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

public interface IGameController {

  
    /**
     * Get the player's sprite.
     * 
     * @return The player sprite.
     */
    CoordinateSprite getPlayerSprite();

    
    void changeWalkingDirection(Direction direction, CoordinateSprite sprite);

    /**
     * Change the foot type to the given foot type.
     * 
     * @param walking The type of walking you want to use.
     */
    void changeFootType(FootType walking, CoordinateSprite object);

    /**
     * Returns the type of foot that the player is using to walk.
     * 
     * @return The walking type of the person.
     */
    FootType getWalkingType(CoordinateSprite object);

   
   
    /**
     * Returns true if there is a bullet in the chamber, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean isBulletGonnaShoot();

    /**
     * When a bullet hits something, do this.
     * 
     * @param object The bullet that hit the object.
     */
    void bulletHit(Bullet object);

    /**
     * Get all the bullets in the game.
     * 
     * @return An ArrayList of all the bullets in the game.
     */
    List<Bullet> getAllBullets();

    List<Bullet> getCopyOfBullets();

    void moveAllBullets();

    void checkAndDamageBullets();
    

    /**
     * Loads a bullet into the bullet array, and sets the bullet's movement to the specified movementx
     * and movementy values.
     * 
     * @param b true if the bullet is a player bullet, false if it's an enemy bullet
     * @param movementx The x-axis movement of the bullet.
     * @param movementy The y-axis movement of the bullet.
     */
    void loadBullet(boolean b, int movementx, int movementy,Coordinate startCoordinate);

    /**
     * Resets the bullet's position to the player's position
     */
    void resetBullet();

    /**
     * This function takes a list of bullets and replaces the current list of bullets with the new
     * list.
     * 
     * @param newBullets The new list of bullets to replace the old one.
     */
    void changeBullets(List<Bullet> newBullets);


    public CoordinateSprite damageObject(CoordinateSprite damageObject, CoordinateSprite reciever);

    /**
     * Moves the object in the direction of the deltaRow and deltaColumn by one space
     * 
     * @param deltaRow The number of rows to move the object.
     * @param deltaColumn The number of columns to move the object.
     * @param object The object you want to move.
     * @return The object that is being moved.
     */
    public <E> Object moveObject(int deltaRow, int deltaColumn, E object);
    

    /**
     * This function is called every time the monster should do anything, and the function 
     * exequtes every thing the monster should do
     * @return 
     */
    public List<CoordinateSprite> monsterStep();

    public List<CoordinateSprite> getEnemySprites();


    public <E> boolean simpleCollision (E attacker,CoordinateSprite victim);

    void changeEnemies(List<CoordinateSprite> newEnemies);

    boolean SpriteCollision(CoordinateSprite attacker, CoordinateSprite victim);

    /**
     * Returns true if an enemy exists, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean enemyExists();

    public CoordinateSprite getTrapDoor();

    void activateTrapDoor();

    void nextFloor();

    public int getTimeBetweenShots();
      
       
    public void activatePowerUps();

    public void powerUpChecker();

    public void changeGameState(GameState newState);
    public GameState getGameState();

    public Coordinate directiontoCoordinate(CoordinateSprite sprite);

    

    


    

   
}
