package inf101.game.controller;

import inf101.game.view.States.FootType;
import inf101.game.view.States.PlayerDirection;

public interface IGameController {


    boolean movePlayer(int deltaRow,int  deltaColumn);

    void changeWalkingDirection(PlayerDirection direction);

    void changeFootType(FootType walking);

    FootType getWalkingType();

    PlayerDirection getPlayerDirection();

    
    
}