package com.mukund.mvcsecurity.exceptionhandler;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
/**
 * Exception handler class
 * 
 * @since 1.0
 * @author MukundBhardwaj
 */
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<ErrorResponse> handleException(ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON)).body(
                        new ErrorResponse(404, "Not Found", request.getRequestURI(), ZonedDateTime.now(),
                                ex.getMessage(),
                                null));
    }

}