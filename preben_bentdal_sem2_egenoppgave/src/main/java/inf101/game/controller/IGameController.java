package inf101.game.controller;

import java.util.List;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.grid.Coordinate;
import inf101.model.Bullet;
import inf101.model.Sprite.CoordinateSprite;

public interface IGameController {

    boolean moveObject(int deltaRow,int  deltaColumn, CoordinateSprite object);

    boolean moveBullet(int deltaRow,int  deltaColumn, Bullet object);

    CoordinateSprite getPlayer();

    void changeWalkingDirection(PlayerDirection direction);

    void changeFootType(FootType walking);

    void resetAcceleration();

    FootType getWalkingType();

    PlayerDirection getPlayerDirection();

    void loadBullet(boolean shot);

    boolean bulletInChaimber();

    void bulletHit(Bullet object);

    List<Bullet> getAllBullets();

    void loadBullet(boolean b, int movementx, int movementy);



    

    
    
}
