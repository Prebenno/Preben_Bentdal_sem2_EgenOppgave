package inf101.game.view;

import java.util.List;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.grid.Coordinate;
import inf101.grid.itemWithCoordinate;
import inf101.model.Bullet;
import inf101.model.Pixel;
import inf101.model.Sprite.CoordinateSprite;

public interface iRoomview {

    /** @return number of rows in grid */
    int getRows();

    /** @return number of columns in grid */
    int getColumns();

    /**
     * "Get all the pixels in the image, along with their coordinates."
     * 
     * The coordinate is a pair of integers, one of which is the x-coordinate and the other of which is
     * the y-coordinate
     * 
     * @return An iterable of itemWithCoordinate<Pixel>
     */
    Iterable<itemWithCoordinate<Pixel>> getPixels();

    /**
     * "Get all the pixels in the sprite, along with their coordinates."
     * 
     * @return An iterable of itemWithCoordinate<Pixel>
     */
    Iterable<itemWithCoordinate<Pixel>> getSpritePixels();

    /**
     * Get all the bullets in the game.
     * 
     * @return An ArrayList of all the bullets in the game.
     */
    List<Bullet> getAllBullets();

    /**
     * Returns the center of the grid
     * 
     * @return A Coordinate object.
     */
    Coordinate getCenter();

    /**
     * This function returns the height of the player.
     * 
     * @return The height of the player.
     */
    int getPlayerHeight();

    /**
     * This function returns the width of the player.
     * 
     * @return The width of the player.
     */
    int getPlayerWidth();

    /**
     * This function returns a CoordinateSprite object that represents the player's sprite.
     * 
     * @return The player sprite.
     */
    CoordinateSprite getPlayerSprite();

    /**
     * Get the sprite of the enemy.
     * 
     * @return The enemy sprite.
     */
    CoordinateSprite getEnemySprite();

    
    

    /**
     * If the bullet is going to shoot, return true, otherwise return false.
     * 
     * @return A boolean value.
     */
    boolean isBulletGonnaShoot();


    /**
     * Get the pixels that are part of the enemy's
     * 
     * @return An iterable of itemWithCoordinate<Pixel>
     */
    Iterable<itemWithCoordinate<Pixel>> getEnemyTestPixels();


    /**
     * Returns true if an enemy exists, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean enemyExsists();


   

   
    


    
}
