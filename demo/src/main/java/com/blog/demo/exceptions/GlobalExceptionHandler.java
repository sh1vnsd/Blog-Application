package com.blog.demo.exceptions;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.blog.demo.payloads.ApiResponse;

@RestControllerAdvice //This anotation will make this class exception handler
public class GlobalExceptionHandler {
    

    //Exception will be handled of this class below
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage(); //From here you will get the message that is thrown from ResourceNotFoundException Class
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handelMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> resp = new HashMap<>();
                             //using this we will get a list which will have object of errors 
                                            //using forEach to traverse the list to get all the errors
                                                            //Using lamda we will get the data from every field
        ex.getBindingResult().getAllErrors().forEach((error)->{
             //we will typecast error into FieldError because we cannot use getField in error 
            String fieldName = ((FieldError)error).getField(); //Will be giving the name of the field
            String message = error.getDefaultMessage(); 
            resp.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    }
}