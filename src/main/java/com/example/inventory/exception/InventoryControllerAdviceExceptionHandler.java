package com.example.inventory.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Error handler class
 */
@ControllerAdvice
public class InventoryControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentExceptionExceptionHandler(IllegalArgumentException ex, WebRequest request) throws IOException {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        return buildResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage(), path);
    }

    @ExceptionHandler(InventoryDuplicateException.class)
    public ResponseEntity<Object> inventoryDuplcateExceptionHandler(InventoryDuplicateException ex, WebRequest request) throws IOException {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        return buildResponseEntity(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.name(), ex.getMessage(), path);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<Object> inventoryNotFoundExceptionHandler(InventoryNotFoundException ex, WebRequest request) throws IOException {
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();
        return buildResponseEntity(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), ex.getMessage(), path);
    }

    /** Overrides the existing handler so we can insert our own error message based on the
     * validation annotations of the entity object
     *
     * @param ex the exception
     * @param headers http headers
     * @param status http status
     * @param request http request
     * @return ResponseEntity JSON containing error info
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> messages = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> x.getDefaultMessage())
            .collect(Collectors.toList());

        String message = messages.get(0);
        String path = ((ServletWebRequest)request).getRequest().getRequestURI();

        return buildResponseEntity(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), message, path);
    }

    /**
     * Builds a JSON response containing the error details
     *
     * @param statusCode http status code
     * @param errorMessage http error, eg BAD_REQUEST
     * @param message human readable error
     * @param path path
     * @return
     */
    private ResponseEntity<Object> buildResponseEntity(int statusCode, String errorMessage, String message, String path) {
        ApiExceptionResponse apiError = new ApiExceptionResponse(Integer.toString(statusCode), errorMessage, message, path);
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(statusCode));
    }

}

