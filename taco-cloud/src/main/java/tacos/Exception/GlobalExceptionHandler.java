package tacos.Exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

//    it handles all order exception
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorResponse> handleOrderException(OrderException ex) {
    	    ErrorResponse res=new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    //    it handles all taco exception
    @ExceptionHandler(TacoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTacoNotFoundException(TacoNotFoundException ex) {
     	ErrorResponse res=new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
    }

    //    it handles all ingredient exception
    @ExceptionHandler(IngredientException.class)
    public ResponseEntity<ErrorResponse> handleIngredientException(IngredientException ex) {
    	     ErrorResponse res=new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
   
    @ExceptionHandler(value=MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    	     Map<String,String> errorMap=new HashMap<>();
    	      BindingResult error=ex.getBindingResult();
    	      List<FieldError> errorList=error.getFieldErrors();
    	      for(FieldError err:errorList) {
    	    	       errorMap.put(err.getField(),err.getDefaultMessage());
    	      }
    	      return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);    	      
    }
    
    @ExceptionHandler(value=Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
    	ErrorResponse res=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    	 return new ResponseEntity<>(res,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
