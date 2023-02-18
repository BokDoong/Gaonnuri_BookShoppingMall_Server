package GaonNuri.Project.ShoppingMall.order.exception;

public class OutOfStockException extends RuntimeException{

    public OutOfStockException(String msg){
        super(msg);
    }
}
