package inf101.model.Sprite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate startPos;
    public int MAXENEMIES = 1;
    

    public void setSenterColumn(){
        this.startPos = new Coordinate(0,0); // player spawnpoint not on walls

    }
    public CoordinateSprite getTrapDoor(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.TRAPDOOR, new Coordinate (250,250) ,999999999);
        return myCordSprite;
    }
 
    public CoordinateSprite getPlayer(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.PLAYER, new Coordinate(250,250) ,1000);
        return myCordSprite;

    }
    public CoordinateSprite getBulletSprite(Coordinate position){
        CoordinateSprite myBullet = new CoordinateSprite (SpriteValues.BULLET, position,0);
        return myBullet;
    }
    public CoordinateSprite getShooter(Coordinate posision){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.SHOOTER, posision ,50);
        return myCordSprite;
    }
    public CoordinateSprite getRunner(Coordinate posision){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.RUNNER, posision ,100);
        return myCordSprite;
    }
    
    public CoordinateSprite getHealth(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.HEALTH,new Coordinate(100,100),99999);
        return myCordSprite;
    }
    public CoordinateSprite getScoreIncrease(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.SCOREMULTI,new Coordinate(100,200),99999);
        return myCordSprite;
    }
    public CoordinateSprite getAttackIncrease(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.DAMAGEUP,new Coordinate(100,300),99999);
        return myCordSprite;
    }
    public CoordinateSprite getAttackSpeedIncrease(){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.ATTACKSPEED,new Coordinate(100,400),99999);
        return myCordSprite;
    }

    public List<CoordinateSprite> getEnemyWave(){
        int numberOfEnemies = ThreadLocalRandom.current().nextInt(1, MAXENEMIES+1 );
        List<Coordinate> validCoordinates = Arrays.asList(new Coordinate(50,50),new Coordinate(450,50),new Coordinate(50,450),new Coordinate(450,450));
        List<CoordinateSprite> newSprites = new ArrayList<CoordinateSprite>();
        for (int enemy = 0; enemy < numberOfEnemies; enemy++) {
            int randomNumber = ThreadLocalRandom.current().nextInt(1, 3);
            Coordinate validCoordinate = validCoordinates.get(enemy);
            if (randomNumber == 1){
                newSprites.add(getRunner(validCoordinate));}
            if (randomNumber == 2){
                newSprites.add(getShooter(validCoordinate));
            }   
        }
        return newSprites;
    }
    public void addEnemy(){
        if (MAXENEMIES < 4){
            MAXENEMIES++;
        }
    }
    
}
