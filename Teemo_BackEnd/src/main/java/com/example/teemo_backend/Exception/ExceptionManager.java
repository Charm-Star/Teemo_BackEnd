package com.example.teemo_backend.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runTimeExceptionHandler(RuntimeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }


    @ExceptionHandler(AppException.class)
    public ResponseEntity appTimeExceptionHandler(AppException e){
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(e.getErrorCode().name()+" "+e.getMessage());
    }
}
