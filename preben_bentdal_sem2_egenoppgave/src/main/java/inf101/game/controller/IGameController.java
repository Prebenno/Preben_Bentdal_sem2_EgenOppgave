package inf101.game.controller;

import java.util.List;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.grid.Coordinate;
import inf101.model.Bullet;
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
    boolean moveObject(int deltaRow,int  deltaColumn, CoordinateSprite object);

    /**
     * boolean moveBullet(int deltaRow,int  deltaColumn, Bullet object);
     * 
     * @param deltaRow The number of rows to move the object.
     * @param deltaColumn The number of columns to move the object.
     * @param object The object that you want to move.
     * @return A boolean value.
     */
    Bullet moveBullet(int deltaRow,int  deltaColumn, Bullet object);

    /**
     * Get the player's sprite.
     * 
     * @return The player sprite.
     */
    CoordinateSprite getPlayer();

    /**
     * This function changes the direction the player is walking in.
     * 
     * @param direction The direction the player is walking in.
     */
    void changeWalkingDirection(PlayerDirection direction);

    /**
     * Change the foot type to the given foot type.
     * 
     * @param walking The type of walking you want to use.
     */
    void changeFootType(FootType walking);

    /**
     * Resets the acceleration to zero
     */
    void resetAcceleration();

    /**
     * Returns the type of foot that the player is using to walk.
     * 
     * @return The walking type of the person.
     */
    FootType getWalkingType();

    /**
     * This function returns the direction the player is facing.
     * 
     * @return The direction the player is facing.
     */
    PlayerDirection getPlayerDirection();

   
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

    /**
     * Loads a bullet into the bullet array, and sets the bullet's movement to the specified movementx
     * and movementy values.
     * 
     * @param b true if the bullet is a player bullet, false if it's an enemy bullet
     * @param movementx The x-axis movement of the bullet.
     * @param movementy The y-axis movement of the bullet.
     */
    void loadBullet(boolean b, int movementx, int movementy);

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
    boolean checkAndDamage(Bullet bullet);

    public <T> T MOVESOMETHING(int deltaRow, int deltaColumn, T object);
    
}
