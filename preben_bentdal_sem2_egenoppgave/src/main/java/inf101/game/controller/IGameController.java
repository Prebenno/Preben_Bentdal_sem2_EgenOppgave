package inf101.game.controller;

import java.util.List;

import inf101.game.States.GameState;
import inf101.grid.Coordinate;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

public interface IGameController {

    /**
     * This function is used to get the CoordinateSprite witch represents the player
     * 
     * @return The player sprite.
     */
    CoordinateSprite getPlayerSprite();

    
    /**
     * Returns true if there is a bullet in the chamber, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean isBulletGonnaShoot();

    /**
     * Get all the bullets in the game.
     * 
     * @return An ArrayList of all the bullets in the game.
     */
    List<Bullet> getAllBullets();

    /**
     * This function moves all the bullets in the game according to the bullets x and y direction
     */
    void moveAllBullets();

    /**
     * This function checks if the bullets are colliding with the enemies and/or player and if they are, it damages
     * the specified CoordinateSprite
     */
    void checkAndDamageBullets();
    


    /**
     * This function loads a bullet into the game
     * 
     * @param b true if the bullet is fired by the player, false if it's fired by the enemy
     * @param movementx The x-coordinate of the bullet's movement.
     * @param movementy The y-axis movement of the bullet.
     * @param startCoordinate The coordinate where the bullet will start from.
     */
    void loadBullet(boolean b, int movementx, int movementy,Coordinate startCoordinate);

    /**
     * This function damages the reciever sprite with the Object
     * 
     * @param damageObject The object that is doing the damage.
     * @param reciever The object that is being damaged.
     * @return The damageObject is being returned.
     */
    public CoordinateSprite damageObject(CoordinateSprite damageObject, CoordinateSprite reciever);

    /**
     * Move the object in the direction specified by the deltaRow and deltaColumn parameters, and
     * return the moved Object if move is valid, object is generic to move bullets and CoordinateSprite.
     * 
     * @param deltaRow The number of rows to move the object.
     * @param deltaColumn The number of columns to move the object.
     * @param object The object to move.
     * @return The object that was moved.
     */
    public <E> Object moveObject(int deltaRow, int deltaColumn, E object);
    

    
    /**
     * This function is called every time the monster should take a step.
     */
    public void monsterStep();

    /**
     * This function returns true if the attacker and victim are colliding
     * SimpleCollision is used for detecting collisions where an estimate it better as its
     * Big o time is significatly less than SpriteColision
     * @param attacker The attacking sprite.
     * @param victim The victim of the attack.
     * @return A boolean value.
     */
    public <E> boolean simpleCollision (E attacker,CoordinateSprite victim);

    /**
     * Returns true if the attacker sprite is colliding with the victim sprite
     * This is "True collision" witch takes a lot more thime than simpleCollision but is easier on the cpu. 
     * @param attacker The attacking sprite.
     * @param victim The victim of the attack.
     * @return A boolean value.
     */
    boolean SpriteCollision(CoordinateSprite attacker, CoordinateSprite victim);

    /**
     * Returns true if an enemy exists, false otherwise.
     * 
     * @return A boolean value.
     */
    boolean enemyExists();

    /**
     * Returns the trap door sprite.
     * 
     * @return A CoordinateSprite object.
     */
    public CoordinateSprite getTrapDoor();

    /**
     * Activates the trap door.
     */
    void activateTrapDoor();

    /**
     * This function will move the sprite to the next floor and generate new values for that floor.
     */
    void nextFloor();

    /**
     * Returns the time between shots.
     * 
     * @return The time between shots.
     */
    public int getTimeBetweenShots();
      
       
    /**
     * Activate the powerups, witch means that the powerups are added to the arraylist and painted.
     */
    public void activatePowerUps();

    
    /**
     * This function checks if the player is colliding with any powerups and collects them if he does
     */
    public void powerUpChecker();

    /**
     * This function changes the game state to the new state.
     * 
     * @param newState The new state to change to.
     */
    public void changeGameState(GameState newState);
    /**
     * Returns the current state of the game.
     * 
     * @return The current game state.
     */
    public GameState getGameState();

    /**
     * This function takes a sprite and returns the direction of the sprite represented as a coordinate.
     * 
     * @param sprite The sprite you want to get the direction to.
     * @return A coordinate.
     */
    public Coordinate directiontoCoordinate(CoordinateSprite sprite);

     /**
     * This function returns the floor number that the player is on
     * 
     * @return The floor number of the player.
     */
    int getFloorNum();


    

    


    

   
}
