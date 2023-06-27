package com.mukund.mvcsecurity.exceptionhandler;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
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

        private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                LOGGER.error("Exception Occurred method argument not valid", ex);
                return ResponseEntity.status(status).body(new ErrorResponse(status.value(),
                                status.toString().split("\s", 2)[1],
                                ((ServletWebRequest) request).getRequest().getRequestURI(), ZonedDateTime.now(),
                                "Invalid Field Values",
                                ex.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList()));
        }

        @Override
        protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                LOGGER.error("Exception Occurred for method not supported", ex);
                return ResponseEntity.status(status).body(new ErrorResponse(status.value(),
                                status.toString().split("\s", 2)[1],
                                ((ServletWebRequest) request).getRequest().getRequestURI(), ZonedDateTime.now(),
                                ex.getLocalizedMessage(), null));
        }

        @ExceptionHandler({ ResourceNotFoundException.class })
        public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex,
                        HttpServletRequest request) {
                LOGGER.error("Exception Occurred for Resource not found", ex);
                return ResponseEntity.status(HttpStatusCode.valueOf(404))
                                .headers(headers -> headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON)).body(
                                                new ErrorResponse(404, "Not Found", request.getRequestURI(),
                                                                ZonedDateTime.now(),
                                                                ex.getMessage(),
                                                                null));
        }

        @ExceptionHandler({ Exception.class })
        public ResponseEntity<ErrorResponse> handleException(Exception ex,
                        HttpServletRequest request) {
                LOGGER.error("Exception Occurred", ex);
                return ResponseEntity.status(HttpStatusCode.valueOf(500))
                                .headers(headers -> headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON)).body(
                                                new ErrorResponse(500, "Internal Server Error", request.getRequestURI(),
                                                                ZonedDateTime.now(),
                                                                ex.getLocalizedMessage(),
                                                                null));
        }

        @ExceptionHandler({ AccessDeniedException.class })
        public ResponseEntity<ErrorResponse> handleException(AccessDeniedException ex,
                        HttpServletRequest request) {
                LOGGER.error("Exception Occurred for Access Denied", ex);
                return ResponseEntity.status(HttpStatusCode.valueOf(403))
                                .headers(headers -> headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON)).body(
                                                new ErrorResponse(403, "Forbidden", request.getRequestURI(),
                                                                ZonedDateTime.now(),
                                                                ex.getLocalizedMessage(),
                                                                null));
        }

        @ExceptionHandler({ BadCredentialsException.class })
        public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex,
                        HttpServletRequest request) {
                LOGGER.error("Exception Occurred for Bad Credentials", ex);
                return ResponseEntity.status(HttpStatusCode.valueOf(400))
                                .headers(headers -> headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON)).body(
                                                new ErrorResponse(400, "Bad Request", request.getRequestURI(),
                                                                ZonedDateTime.now(),
                                                                ex.getLocalizedMessage(),
                                                                null));
        }

}