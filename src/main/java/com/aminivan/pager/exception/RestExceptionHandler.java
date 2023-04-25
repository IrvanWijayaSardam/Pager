package com.aminivan.pager.exception;

import com.aminivan.pager.utils.ResponseHandler;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleSqlException(final SQLException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }
    @ExceptionHandler({ DataAccessException.class })
    public ResponseEntity<Object> handleDataAccessException(final DataAccessException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleAll(final RuntimeException ex, final WebRequest request) {
        logger.info(ex.getClass().getName());
        logger.error("error", ex);

        return ResponseHandler.generateResponse(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}