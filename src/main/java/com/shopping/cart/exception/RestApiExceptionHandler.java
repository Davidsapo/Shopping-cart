package com.shopping.cart.exception;

import com.shopping.cart.exception.exceptions.IdException;
import com.shopping.cart.exception.exceptions.NonUniqueValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestApiException> handleValidationException(MethodArgumentNotValidException exception) {
        StringBuilder message = new StringBuilder();
        exception.getFieldErrors().forEach(fieldError -> message.append(String.format("%s : %s ", fieldError.getField(),
                fieldError.getDefaultMessage())));
        RestApiException restApiException = new RestApiException(HttpStatus.BAD_REQUEST, message.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restApiException);
    }

    @ExceptionHandler(IdException.class)
    public ResponseEntity<RestApiException> handleIdException(IdException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restApiException);
    }

    @ExceptionHandler(NonUniqueValueException.class)
    public ResponseEntity<RestApiException> handleNonUniqueValueException(NonUniqueValueException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restApiException);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<RestApiException> handleNoSuchElementException(NoSuchElementException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restApiException);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestApiException> handleNAccessDeniedException(AccessDeniedException exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(restApiException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestApiException> handleException(Exception exception) {
        RestApiException restApiException = new RestApiException(HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getClass().getName() + ": " + exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(restApiException);
    }

}
