package inf101.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
        board.loadBullet(true, 1, 1,board.getPlayerSprite().getCoordinate());
        assertTrue(board.isBulletGonnaShoot());

        board.getAllBullets().remove((board.getAllBullets().get(0)));
        assertFalse(board.isBulletGonnaShoot());
        
        for (int bulletC = 1; bulletC < 10; bulletC++) {
            board.loadBullet(true,1,1,board.getPlayerSprite().getCoordinate());
            assertEquals(bulletC, board.getAllBullets().size());   
        }   
    }
    @Test
    void collisionTest(){
        board.loadBullet(true,1,0,new Coordinate(50,50));
        CoordinateSprite enemy =board.spawner.getRunner(new Coordinate (450,450));
        Bullet bullet = board.getAllBullets().get(0);
        Coordinate pathToEnemy = new Coordinate(enemy.getCoordinate().getRow()-bullet.getShape().getCoordinate().getRow(),
                                                enemy.getCoordinate().getColumn()-bullet.getShape().getCoordinate().getColumn());

        assertFalse(board.SpriteCollision(bullet.getShape(), enemy));
        Bullet newBullet = (Bullet) board.moveObject(pathToEnemy.getRow(),pathToEnemy.getRow(), bullet);
        board.resetBullet();
        List<Bullet> newBullets = new ArrayList<Bullet>();
        newBullets.add(newBullet);
        board.changeBullets(newBullets);
        assertTrue(board.SpriteCollision(board.getAllBullets().get(0).getShape(), enemy));
    }
    @Test
    void spawnerTest() throws OutOfBoundsException{
        //GameModel board = new GameModel();
        CoordinateSprite health = board.spawner.getHealth();
        CoordinateSprite damage = board.spawner.getAttackIncrease();
        CoordinateSprite attackSpeedUp = board.spawner.getAttackSpeedIncrease();
        CoordinateSprite scoreIncrease = board.spawner.getScoreIncrease();

        board.activatePowerUps(); //Health checker
        board.moveObject(51,51,board.getPlayerSprite());
        assertEquals(board.getPlayerSprite().getCoordinate(),new Coordinate(101,101));
        assertTrue(board.simpleCollision(board.getPlayerSprite(),health));
        assertEquals(board.getPlayerSprite().getHealth(),1000);
        board.powerUpChecker();
        assertEquals(board.getPlayerSprite().getHealth(),1200);

        board.activatePowerUps(); // Checking if it works multiple times or just sets value
        board.powerUpChecker();
        assertEquals(board.getPlayerSprite().getHealth(),(int) 1200*1.2);


        board.activatePowerUps(); // i have to reactivate powerups Score Increase checker
        board.moveObject(0,100,board.getPlayerSprite());
        assertEquals(board.getPlayerSprite().getCoordinate(),new Coordinate(101,201));
        assertTrue(board.simpleCollision(board.getPlayerSprite(),scoreIncrease));
        assertEquals(board.getScoreMultiplier(),1);
        board.powerUpChecker();
        assertEquals(board.getScoreMultiplier(),2);

        board.activatePowerUps(); // Bullet Damage Checker
        board.moveObject(0,100,board.getPlayerSprite());
        assertEquals(board.getPlayerSprite().getCoordinate(),new Coordinate(101,301));
        assertTrue(board.simpleCollision(board.getPlayerSprite(),damage));
        assertEquals(board.getPlayerBulletDamage(),10);
        board.powerUpChecker();
        assertEquals(board.getPlayerBulletDamage(),20);

        board.activatePowerUps();  //Attack speed Checker
        board.moveObject(0,100,board.getPlayerSprite());
        assertEquals(board.getPlayerSprite().getCoordinate(),new Coordinate(101,401));
        assertTrue(board.simpleCollision(board.getPlayerSprite(),attackSpeedUp));
        assertEquals(board.getTimeBetweenShots(),500);
        board.powerUpChecker();
        assertEquals(board.getTimeBetweenShots(),(int) 500*0.8);
             
        
    }
    
    


    public static void main(String[] args) throws OutOfBoundsException {
       
    }
        
    
}
