package inf101.grid;

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


    public int getColumn() {
        return this.column;
    }
    
    public boolean equals(Coordinate cord1, Coordinate cord2) {
        return ((cord1.getRow() == cord2.getRow()) && (cord1.getColumn() == cord2.getColumn()));

    }

    
}
