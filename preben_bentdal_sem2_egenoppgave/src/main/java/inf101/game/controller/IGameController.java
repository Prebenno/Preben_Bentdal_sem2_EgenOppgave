package inf101.game.controller;

import java.util.List;

import inf101.game.States.FootType;
import inf101.game.States.Direction;
import inf101.grid.Coordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

public interface IGameController {

   /**
    * This function moves the object to the new location if the new location is valid
    * 
    * @param deltaRow The number of rows to move the object.
    * @param deltaColumn The number of columns to move the object.
    * @param object The object you want to move.
    * @return A boolean value.
    */
    //boolean moveObject(int deltaRow,int  deltaColumn, CoordinateSprite object); old functions

    /**
     * boolean moveBullet(int deltaRow,int  deltaColumn, Bullet object);
     * 
     * @param deltaRow The number of rows to move the object.
     * @param deltaColumn The number of columns to move the object.
     * @param object The object that you want to move.
     * @return A boolean value.
     */
    //Bullet moveBullet(int deltaRow,int  deltaColumn, Bullet object); old functions

    /**
     * Get the player's sprite.
     * 
     * @return The player sprite.
     */
    CoordinateSprite getPlayer();

    
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
     * This function returns the direction the player is facing.
     * 
     * @return The direction the player is facing.
     */
    Direction getDirection();

   
    /**
     * Returns true if there is a bullet in the chamber, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean bulletInChaimber();

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

    List<Bullet> getBulletCopy();

    /**
     * Loads a bullet into the bullet array, and sets the bullet's movement to the specified movementx
     * and movementy values.
     * 
     * @param b true if the bullet is a player bullet, false if it's an enemy bullet
     * @param movementx The x-axis movement of the bullet.
     * @param movementy The y-axis movement of the bullet.
     */
    void loadBullet(boolean b, int movementx, int movementy);

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

    /**
     * Check if the bullet is colliding with the player, and if so, damage the player.
     * 
     * @param bullet The bullet that is being checked for collision.
     * @return 
     */
    CoordinateSprite damageObject(CoordinateSprite damageObject, CoordinateSprite reciever);

    /**
     * Moves the object in the direction of the deltaRow and deltaColumn by one space
     * 
     * @param deltaRow The number of rows to move the object.
     * @param deltaColumn The number of columns to move the object.
     * @param object The object you want to move.
     * @return The object that is being moved.
     */
    public <T> T moveObject(int deltaRow, int deltaColumn, T object);
    

    /**
     * This function is called every time the monster should do anything, and the function 
     * exequtes every thing the monster should do
     * @return 
     */
    public List<CoordinateSprite> monsterStep();

    public List<CoordinateSprite> getEnemies();


    boolean didDamage(CoordinateSprite spriteOne, CoordinateSprite spriteTwo);

   

    
    public <E> boolean simpleCollision (E attacker,CoordinateSprite victim);

    void changeEnemies(List<CoordinateSprite> newEnemies);

    boolean newCollision(CoordinateSprite attacker, CoordinateSprite victim);

    /**
     * Returns true if an enemy exists, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean enemyExists();

    public CoordinateSprite getTrapDoor();
        
    


   
}
