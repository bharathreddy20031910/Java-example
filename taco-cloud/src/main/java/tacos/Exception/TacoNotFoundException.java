package tacos.Exception;

public class TacoNotFoundException extends RuntimeException{

    public TacoNotFoundException(String mess){
        super(mess);
    }
}
