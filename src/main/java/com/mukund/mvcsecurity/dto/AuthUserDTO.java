package com.mukund.mvcsecurity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object record for AuthUser
 * 
 * @since 1.0
 * @author MukundBhardwaj
 */
public record AuthUserDTO(

        @NotEmpty(message = "name cannot be empty") String name,
        @NotEmpty(message = "email cannot be empty") String email,
        @NotEmpty(message = "password cannot be empty") @JsonProperty(access = Access.WRITE_ONLY) String password,
        @NotNull(message = "active cannot be null") Boolean active) {

}
