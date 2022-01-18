package com.example.upandownloads.exception;


import com.example.upandownloads.utiles.MensajeRespuesta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class ExcepcionesHandler extends ResponseEntityExceptionHandler {



    // Para varios escenarios
    @ExceptionHandler(ExceptionToni.class)
    public final ResponseEntity<CustomError> handleNotFoundException(ExceptionToni ex) {

        CustomError exceptionResponse = new CustomError(new Date(), ex.getMessage(), 404);
        return new ResponseEntity<CustomError>(exceptionResponse, HttpStatus.NOT_ACCEPTABLE);

    }



    //Esta excepción "salta" sólo cuando el tamaño del archivo es superior al indicado en "application.properties"
    //Averiguo la máxima longitud del archivo a subir declarada en "application.properties"
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;


    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public final ResponseEntity<CustomError> handleMaxSizeException(MaxUploadSizeExceededException exc) {

        String mensError = "Archivo > " + maxFileSize + ". Ver 'max-file-size' en 'application.properties'";
        CustomError exceptionResponse1 = new CustomError(new Date(), mensError, 404);
        return new ResponseEntity<CustomError>(exceptionResponse1, HttpStatus.NOT_ACCEPTABLE);

    }

}