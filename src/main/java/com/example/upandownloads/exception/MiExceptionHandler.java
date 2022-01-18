package com.example.upandownloads.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class MiExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ExceptionToni.class)
    public final ResponseEntity<CustomError> handleNotFoundException(ExceptionToni ex) {

        CustomError exceptionResponse = new CustomError(new Date(), ex.getMessage(), 404);
        return new ResponseEntity<CustomError>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

    }
}