package inf101.game.controller;

import inf101.game.States.FootType;
import inf101.game.States.PlayerDirection;

public interface IGameController {


    boolean movePlayer(int deltaRow,int  deltaColumn);

    void changeWalkingDirection(PlayerDirection direction);

    void changeFootType(FootType walking);

    void resetAcceleration();

    FootType getWalkingType();

    PlayerDirection getPlayerDirection();

    
    
}
