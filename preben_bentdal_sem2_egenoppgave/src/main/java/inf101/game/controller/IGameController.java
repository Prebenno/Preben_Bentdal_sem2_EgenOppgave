package inf101.game.controller;

import java.util.List;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;
import inf101.model.Sprite.CoordinateSprite;

public interface IGameController {


    boolean moveObject(int deltaRow,int  deltaColumn, CoordinateSprite object);

    CoordinateSprite getPlayer();

    void changeWalkingDirection(PlayerDirection direction);

    void changeFootType(FootType walking);

    void resetAcceleration();

    FootType getWalkingType();

    PlayerDirection getPlayerDirection();

    CoordinateSprite getFirstBullet();

    void loadBullet(boolean shot);

    boolean bulletInChaimber();

    void bulletHit();

    List<CoordinateSprite> getAllBullets();



    

    
    
}
