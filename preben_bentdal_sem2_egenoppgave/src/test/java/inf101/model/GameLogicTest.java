package inf101.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf101.game.States.Direction;
import inf101.grid.Coordinate;
import inf101.grid.OutOfBoundsException;
import inf101.model.Sprite.Bullet;
import inf101.model.Sprite.CoordinateSprite;

public class GameLogicTest {
    GameModel board;
    @BeforeEach
	void setUp() throws OutOfBoundsException{
        this.board = new GameModel();
        
    }
    
    @Test
    void rowsAndColumnsTest(){
        assertEquals(board.getRows(),500);
        assertEquals(board.getColumns(),500);
    }
    @Test
    void movementTest(){
        int column = board.getPlayerSprite().getCoordinate().getColumn();
        int row = board.getPlayerSprite().getCoordinate().getRow();
        board.moveObject(10, 10, board.getPlayerSprite());
        Coordinate newCoord = new Coordinate(row+10,column+10);
        assertEquals(board.getPlayerSprite().getCoordinate(),newCoord);

        board.moveObject(-20, -20, board.getPlayerSprite());
        newCoord = new Coordinate(row-10,column-10);
        assertEquals(board.getPlayerSprite().getCoordinate(),newCoord);
    }

    @Test
    void bulletTest(){
        assertFalse(board.isBulletGonnaShoot());
        board.loadBullet(true, 1, 1);
        assertTrue(board.isBulletGonnaShoot());

        board.bulletHit(board.getCopyOfBullets().get(0));
        assertFalse(board.isBulletGonnaShoot());
        
        for (int bulletC = 1; bulletC < 10; bulletC++) {
            board.loadBullet(true,1,1);
            assertEquals(bulletC, board.getCopyOfBullets().size());   
        }   
    }
    @Test
    void collisionTest(){
        board.loadBullet(true,1,1);
        CoordinateSprite enemy =board.getEnemySprites().get(0);
        Bullet bullet = board.getAllBullets().get(0);
        Coordinate pathToEnemy = new Coordinate(enemy.getCoordinate().getRow()-bullet.getShape().getCoordinate().getRow(),
                                                enemy.getCoordinate().getColumn()-bullet.getShape().getCoordinate().getColumn());

        assertFalse(board.newCollision(bullet.getShape(), enemy));
        Bullet newBullet = (Bullet) board.moveObject(pathToEnemy.getRow(),pathToEnemy.getRow(), bullet);
        board.resetBullet();
        List<Bullet> newBullets = new ArrayList<Bullet>();
        newBullets.add(newBullet);
        board.changeBullets(newBullets);

        assertTrue(board.newCollision(board.getAllBullets().get(0).getShape(), enemy));
    }
    


    public static void main(String[] args) throws OutOfBoundsException {
        
    }
        
    
}
