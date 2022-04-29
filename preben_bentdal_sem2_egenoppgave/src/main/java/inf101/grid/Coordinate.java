package inf101.grid;

import java.util.Objects;

public class Coordinate {
    
    private final int row;
    private final int column;

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
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return row == coordinate.row && column == coordinate.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return "{" +
            " row='" + getRow() + "'" +
            ", column='" + getColumn() + "'" +
            "}";
    }
    

    
}
