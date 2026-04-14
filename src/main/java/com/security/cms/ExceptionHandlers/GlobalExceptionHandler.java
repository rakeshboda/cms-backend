package com.security.cms.ExceptionHandlers;


import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){


        List<String> allErrors=new ArrayList<>();

        e.getBindingResult().getAllErrors().forEach(error->{
           allErrors.add( error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(allErrors.get(0));
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<?> handleUnexpectedTypeException(UnexpectedTypeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<?> handleMissingPathVariableException(MissingPathVariableException e){
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }

}
