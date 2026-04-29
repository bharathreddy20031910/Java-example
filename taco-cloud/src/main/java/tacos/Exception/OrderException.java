package tacos.Exception;

public class OrderException extends RuntimeException{
    public OrderException(String mess){
        super(mess);
    }
}
