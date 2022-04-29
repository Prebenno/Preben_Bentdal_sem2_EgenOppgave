package inf101.game.view;

import java.util.List;

import inf101.game.States.GameState;
import inf101.grid.Coordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

public interface iRoomview {

    /** @return number of rows in grid */
    int getRows();

    /** @return number of columns in grid */
    int getColumns();


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
     * This function returns a CoordinateSprite object that represents the player's sprite.
     * 
     * @return The player sprite.
     */
    CoordinateSprite getPlayerSprite();

    
    /**
     * Get the list of enemy sprites.
     * 
     * @return A list of CoordinateSprite objects.
     */
    List<CoordinateSprite> getEnemySprites();

    /**
     * Returns the trap door's CoordinateSprite
     * 
     * @return The trap door represented as a CoordinateSprite.
     */
    CoordinateSprite getTrapDoor();

    /**
     * If the bullet is going to shoot, return true, otherwise return false.
     * 
     * @return A boolean value.
     */
    boolean isBulletGonnaShoot();

    /**
     * Returns true if an enemy exists, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean enemyExists();


    /**
     * GetScore returns the score represented as a int
     * 
     * @return The score of the player.
     */
    int getScore();

    /**
     * This function returns the floor number that the player is on
     * 
     * @return The floor number of the player.
     */
    int getFloorNum();

    /**
     * > Returns a list of all power ups
     * 
     * @return A list of CoordinateSprite objects.
     */
    public List<CoordinateSprite> getPowerUps();


    /**
     * > Returns the current state of the game
     * 
     * @return The current game state.
     */
    public GameState getGameState();


    


   

   
    


    
}
