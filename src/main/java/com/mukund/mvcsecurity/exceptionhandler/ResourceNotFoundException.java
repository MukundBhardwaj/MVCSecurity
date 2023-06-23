package com.mukund.mvcsecurity.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Custom Exception class for Resource Not Found exceptions
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    public ResourceNotFoundException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
