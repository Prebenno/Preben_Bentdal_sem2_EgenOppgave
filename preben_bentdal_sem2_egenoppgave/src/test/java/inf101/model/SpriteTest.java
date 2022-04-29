package inf101.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inf101.game.States.Direction;
import inf101.game.States.FootType;
import inf101.grid.Coordinate;

import inf101.model.Sprite.CoordinateSprite;

public class SpriteTest {
    CoordinateSprite spriteOne;
    CoordinateSprite spriteTwo;
    @BeforeEach
	void setUp(){
        this.spriteOne = new CoordinateSprite();
        this.spriteTwo = new CoordinateSprite();   
    }
    @Test
    void movementTest(){
        Coordinate oldCord = this.spriteOne.getCoordinate();
        assertEquals(oldCord.getRow(),0);
        assertEquals(oldCord.getColumn(),0);
        spriteOne.move(10, 10);
        assertEquals(this.spriteOne.getCoordinate().getRow(),10);
        assertEquals(this.spriteOne.getCoordinate().getColumn(),10);

        assertEquals(this.spriteTwo.getCoordinate().getRow(),0);
        assertEquals(this.spriteTwo.getCoordinate().getColumn(),0);
    }
    @Test 
    void rotateTest(){
        assertEquals(this.spriteOne.getDirection(), Direction.RIGHT);
        this.spriteOne.changeDirection(Direction.UP);
        assertEquals(this.spriteOne.getDirection(), Direction.UP);
        assertEquals(this.spriteTwo.getDirection(), Direction.RIGHT);
    }
    @Test
    void footTypeTest(){
        assertEquals(this.spriteOne.getFootType(), FootType.STAND);
        this.spriteOne.changeFootType(FootType.WALK);
        assertEquals(this.spriteOne.getFootType(), FootType.WALK);

    }

    
}
