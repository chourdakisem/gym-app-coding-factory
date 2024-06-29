package gr.aueb.cf.gymapp.controllers;

import gr.aueb.cf.gymapp.model.ErrorDetails;
import gr.aueb.cf.gymapp.services.exceptions.EntityNotFoundException;
import gr.aueb.cf.gymapp.services.exceptions.RefreshTokenNotValidException;
import gr.aueb.cf.gymapp.services.exceptions.UserNotFoundException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.Arrays;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDetails> entityNotFoundExceptionHandler(EntityNotFoundException e) {
        ErrorDetails error = new ErrorDetails(e.getMessage());
        logger.error(error.getMessage());
        return ResponseEntity
                .status(404)
                .body(error);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userNotFoundExceptionHandler(UserNotFoundException e) {
        ErrorDetails error = new ErrorDetails(e.getMessage());
        logger.error(error.getMessage());
        return ResponseEntity
                .status(404)
                .body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> runtimeExceptionHandler(RuntimeException e) {
        ErrorDetails error = new ErrorDetails(e.getMessage());
        logger.error(error.getMessage());
        if (error.getMessage().startsWith("could not execute statement [Duplicate entry ")) {
            String[] errorMessages = Arrays.copyOfRange(error.getMessage().split(" "), 4, 7);
            String errorMessage = String.join(" ", errorMessages);
            errorMessage = errorMessage.substring(1);
            error.setMessage(errorMessage);
            return ResponseEntity
                    .status(409)
                    .body(error);
        }
        return ResponseEntity
                .status(400)
                .body(error);
    }

    @ExceptionHandler(RefreshTokenNotValidException.class)
    public ResponseEntity<ErrorDetails> refreshTokenExpiredHandler(RefreshTokenNotValidException e) {
        ErrorDetails error = new ErrorDetails((e.getMessage()));
        logger.error(error.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
}
