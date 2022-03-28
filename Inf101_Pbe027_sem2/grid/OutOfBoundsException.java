package Inf101_Pbe027_sem2.grid;

public class OutOfBoundsException extends Exception {
    String msg;
    OutOfBoundsException(String str){
        msg = str;

    }
    public void msgOut(){
        System.out.println("OutOfBoundsException," + msg);

    }

}
