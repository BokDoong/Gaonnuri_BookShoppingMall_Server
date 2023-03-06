package GaonNuri.Project.ShoppingMall.exception;

public class OutOfStockException extends RuntimeException{

    public OutOfStockException(String msg){
        super(msg);
    }
}
