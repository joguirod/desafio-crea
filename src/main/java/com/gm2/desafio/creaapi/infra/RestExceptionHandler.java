package com.gm2.desafio.creaapi.infra;

import com.gm2.desafio.creaapi.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProfessionalNotFoundException.class)
    private ResponseEntity<RestExceptionMessage> ProfessionalNotFoundHandler(ProfessionalNotFoundException exception){
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    private ResponseEntity<RestExceptionMessage> TitleNotFoundHandler(TitleNotFoundException exception){
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }

    @ExceptionHandler(AlreadyExistException.class)
    private ResponseEntity<RestExceptionMessage> AlreadyExistHandler(AlreadyExistException exception){
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(AlreadyHaveException.class)
    private ResponseEntity<RestExceptionMessage> AlreadyHaveHandler(AlreadyHaveException exception){
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(AlreadyIsException.class)
    private ResponseEntity<RestExceptionMessage> AlreadyIsHandler(AlreadyIsException exception){
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponse);
    }

    @ExceptionHandler(InvalidOperationException.class)
    private ResponseEntity<RestExceptionMessage> InvalidOperationHandler(InvalidOperationException exception){
        RestExceptionMessage exceptionResponse = new RestExceptionMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }
}
