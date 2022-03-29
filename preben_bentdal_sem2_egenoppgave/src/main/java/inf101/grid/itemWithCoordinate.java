package inf101.grid;

import java.util.Objects;

public class itemWithCoordinate<E> {

    private final Coordinate coordinate;
    private final E item;


    public itemWithCoordinate(Coordinate coordinate, E item) {
        this.coordinate = coordinate;
        this.item = item;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public E getItem() {
        return this.item;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof itemWithCoordinate)) {
            return false;
        }
        itemWithCoordinate<E> itemWithCoordinate = (itemWithCoordinate) o;
        return Objects.equals(coordinate, itemWithCoordinate.coordinate) && Objects.equals(item, itemWithCoordinate.item);
    }
    

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, item);
    }

    @Override
    public String toString() {
        return "{" +
            " coordinate='" + getCoordinate() + "'" +
            ", item='" + getItem() + "'" +
            "}";
    }
    

    
}
