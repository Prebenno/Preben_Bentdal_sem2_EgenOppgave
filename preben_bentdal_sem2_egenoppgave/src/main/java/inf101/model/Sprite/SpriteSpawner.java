package inf101.model.Sprite;

import inf101.grid.Coordinate;

public class SpriteSpawner {
    public Coordinate startPos;
    

    public void setSenterColumn(){
        this.startPos = new Coordinate(0,0); // player spawnpoint not on walls

    }
    public CoordinateSprite getStarterSprite(){
        return getPlayer(new Coordinate(50,50),10000000);
    }
    public CoordinateSprite getStarterSprite2(){
        return getRunner(new Coordinate(150,150),100);
    }
    public CoordinateSprite getStarterSprite3(){
        return getRunner(new Coordinate(300,300),100);
    }
    public CoordinateSprite getStarterSprite4(){
        return getRunner(new Coordinate(300,450),100);
    }
    public CoordinateSprite getTrapDoor(){
        return getTrapDoor(new Coordinate(250,250),999999999);
    }
    public CoordinateSprite getShooter(Coordinate pos){
        return getShooter(pos,50);
    }

    
    
    public CoordinateSprite getPlayer(Coordinate position, int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.PLAYER, position ,health);
        return myCordSprite;

    }
    public CoordinateSprite getBulletSprite(Coordinate position){
        CoordinateSprite myBullet = new CoordinateSprite (SpriteValues.BULLET, position,0);
        return myBullet;
    }
    public CoordinateSprite getTrapDoor(Coordinate position, int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.TRAPDOOR, position ,health);
        return myCordSprite;

    }
    public CoordinateSprite getShooter(Coordinate posision, int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.SHOOTER, posision ,health);
        return myCordSprite;
    }
    public CoordinateSprite getRunner(Coordinate posision, int health){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.RUNNER, posision ,health);
        return myCordSprite;
    }
    
    public CoordinateSprite getHealth(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.HEALTH,position,99999);
        return myCordSprite;
    }
    public CoordinateSprite getScoreIncrease(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.HEALTH,position,99999);
        return myCordSprite;
    }
    public CoordinateSprite getAttackIncrease(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.HEALTH,position,99999);
        return myCordSprite;
    }
    public CoordinateSprite getAttackSpeedIncrease(Coordinate position){
        CoordinateSprite myCordSprite = new CoordinateSprite (SpriteValues.HEALTH,position,99999);
        return myCordSprite;
    }
    
}
