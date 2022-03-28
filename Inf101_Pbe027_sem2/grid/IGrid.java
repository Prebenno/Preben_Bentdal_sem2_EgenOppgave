package Inf101_Pbe027_sem2.grid;

public interface IGrid<E> {


    /**
     * 
     * @param position the element 
     * @return
     */
    E getElement(Coordinate cord);


    void setElement(E element);


    
}
