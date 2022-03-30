package inf101.model;

import inf101.backround.Floor;
import inf101.game.controller.IGameController;
import inf101.game.view.iRoomview;
import inf101.grid.Coordinate;
import inf101.grid.OutOfBoundsException;
import inf101.grid.itemWithCoordinate;
import inf101.model.Sprite.CoordinateSprite;
import inf101.model.Sprite.SpriteSpawner;

import java.awt.Color;
import java.util.ArrayList;

public class GameModel implements iRoomview ,IGameController {
    Floor myfloor;
    Room myroom;
    CoordinateSprite PlayerSprite;
    public SpriteSpawner spawner;

    public GameModel() throws OutOfBoundsException {
        this.spawner = new SpriteSpawner();
        this.spawner.setSenterColumn();
        this.PlayerSprite = spawner.getPlayerSprite();
        this.myfloor = new Floor();
        this.myroom = myfloor.room;
        
    }
    @Override
    public int getRows() {
        return myroom.getRows();
    }

    @Override
    public int getColumns() {
        
        return myroom.getColumns();
    }
    @Override
    public Iterable<itemWithCoordinate<Pixel>> getPixels() {
        return myroom;
    }
    @Override
    public Iterable<itemWithCoordinate<Pixel>> getSpritePixels() {
        
        return PlayerSprite;
    }
    public Coordinate getCenter(){
        return spawner.center;
    }

    public int getWidth(){
        return PlayerSprite.getWidth();

    }
    public int getHeight(){
        return PlayerSprite.getHeight();

    }
    @Override
    public boolean movePlayer(int deltaRow, int deltaColumn) {
        
        for (itemWithCoordinate<Pixel> coordItem : this.PlayerSprite) { //Checks if all coordinates is on grid
            int col= coordItem.getCoordinate().getColumn();
            int row = coordItem.getCoordinate().getRow();
            Coordinate testcord = new Coordinate(row + deltaRow, col + deltaColumn);
            if ((!(myroom.coordinateOnBoard(testcord))) ){  // if coordinate is not on grid or if colour is not black
                System.out.println("yey");
                return false;
            }   
        }
        this.PlayerSprite = this.PlayerSprite.move(deltaRow, deltaColumn);      
        return true;
        
     
    }
   
    
    
}
