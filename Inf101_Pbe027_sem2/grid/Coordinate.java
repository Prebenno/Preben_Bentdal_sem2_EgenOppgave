package Inf101_Pbe027_sem2.grid;

public class Coordinate {
    public final int row;
    public final int column;

    public Coordinate(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }


    public int getCol() {
        return this.column;
    }
    
    public boolean equals(Coordinate cord1, Coordinate cord2) {
        return ((cord1.getRow() == cord2.getRow()) && (cord1.getCol() == cord2.getCol()));

    }

    
}
