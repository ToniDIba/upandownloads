package com.example.upandownloads.exception;


import com.example.upandownloads.message.ResponseMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    //Máxima longitud del archivo a subir declarada en "application.properties"
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {


        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
               body(new ResponseMessage("Archivo > " + maxFileSize + ". Ver 'max-file-size' en 'application.properties'"));
    }

}