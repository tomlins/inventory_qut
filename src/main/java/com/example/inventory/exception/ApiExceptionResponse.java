package com.example.inventory.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

/**
 * Represents the JSON response for errors
 *
 * {
 *     "timestamp": "01-07-2020 06:23:04",
 *     "status": "400",
 *     "error": "BAD_REQUEST",
 *     "message": "Max value for limit is 50",
 *     "path": "/inventory"
 * }
 *
 */
public class ApiExceptionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String status;
    private String error;
    private String message;
    private String path;

    private ApiExceptionResponse() {
        timestamp = LocalDateTime.now();
    }

    public ApiExceptionResponse(String status, String error, String message, String path) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}