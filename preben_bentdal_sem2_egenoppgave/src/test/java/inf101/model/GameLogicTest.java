package inf101.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void SimpleCollisionTest(){
        board.loadBullet(false,1,0,new Coordinate(50,50));
        CoordinateSprite enemy =board.spawner.getRunner(new Coordinate (450,450));
        Bullet bullet = board.getAllBullets().get(0);
        assertFalse(board.simpleCollision(bullet, enemy));
        board.loadBullet(false,1,0,new Coordinate(451,451));
        Bullet bulletC = board.getAllBullets().get(1);
        assertTrue(board.simpleCollision(bulletC,enemy));
    }
    @Test
    void spawnerTest() throws OutOfBoundsException{
        //GameModel board = new GameModel();
        CoordinateSprite health = board.spawner.getHealth();
        CoordinateSprite damage = board.spawner.getAttackIncrease();
        CoordinateSprite attackSpeedUp = board.spawner.getAttackSpeedIncrease();
        CoordinateSprite scoreIncrease = board.spawner.getScoreIncrease();

        board.activatePowerUps(); //Health checker
        board.moveObject(-149,-149,board.getPlayerSprite());
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

    @Test
    void directiontoCoordinate_test(){
        CoordinateSprite sprite = board.spawner.getPlayer();
        assertTrue(sprite.getDirection().equals(Direction.RIGHT));
        Coordinate right = board.directiontoCoordinate(sprite);
        assertTrue(right.equals(new Coordinate(0,1)));

        sprite.changeDirection(Direction.LEFT);
        Coordinate left = board.directiontoCoordinate(sprite);
        assertTrue(sprite.getDirection().equals(Direction.LEFT));
        assertTrue(left.equals(new Coordinate(0,-1)));

        sprite.changeDirection(Direction.DOWN);
        Coordinate down = board.directiontoCoordinate(sprite);
        assertTrue(sprite.getDirection().equals(Direction.DOWN));
        assertTrue(down.equals(new Coordinate(1,0)));

        sprite.changeDirection(Direction.UP);
        Coordinate up = board.directiontoCoordinate(sprite);
        assertTrue(sprite.getDirection().equals(Direction.UP));
        assertTrue(up.equals(new Coordinate(-1,0)));

    }
    @Test
    void trapDoorTest(){
        board.nextFloor();
        assertTrue(board.getEnemySprites().size()>0);
        assertTrue(board.getTrapDoor() == null);
        board.activateTrapDoor(); //checking if its activated when enemies are on screen
        assertTrue(board.getTrapDoor() == null);

        board.getEnemySprites().clear();
        assertTrue(board.getEnemySprites().size() == 0);
        board.activateTrapDoor(); 
        assertFalse(board.getTrapDoor() == null);
        }

    @Test
    public void advancedColisionTest(){
        board.nextFloor();
        CoordinateSprite enemy = board.getEnemySprites().get(0);
        assertTrue(board.getPlayerSprite().getCoordinate().equals(new Coordinate(250,250)));
        assertTrue(enemy.getCoordinate().equals(new Coordinate (50,50)));
        board.loadBullet(false, 0, 0, new Coordinate(51,51)); // enemy bullet
        assertFalse(board.SpriteCollision(enemy, enemy));

        board.loadBullet(true, 0, 0, new Coordinate(251,251));// player bullet
        assertFalse(board.SpriteCollision(board.getPlayerSprite(), board.getPlayerSprite()));

        board.loadBullet(true, 0, 0, new Coordinate(51,51));// player bullet
        assertTrue(board.SpriteCollision(enemy, enemy.move(-1, -1)));

        board.loadBullet(false, 0, 0, new Coordinate(251,251)); // enemy bullet
        assertTrue(board.SpriteCollision(board.getPlayerSprite(), board.getPlayerSprite().move(-1, -1)));
    }
    
    @Test
    public void monsterStepTest(){
        assertTrue(board.getPlayerSprite().getCoordinate().equals(new Coordinate(250,250)));
        board.getEnemySprites().set(0,board.spawner.getRunner(new Coordinate(50,50)));
        assertTrue(board.getEnemySprites().get(0).getCoordinate().equals(new Coordinate(50,50)));
        board.monsterStep();
        assertTrue(board.getEnemySprites().get(0).getCoordinate().equals(new Coordinate(51,51)));
        board.monsterStep();
        assertTrue(board.getEnemySprites().get(0).getCoordinate().equals(new Coordinate(52,52)));}
       


    
        
    
}
