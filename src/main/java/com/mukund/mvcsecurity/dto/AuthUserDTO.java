package com.mukund.mvcsecurity.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mukund.mvcsecurity.model.AuthUserDetails;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object class for AuthUser containing record for creating and
 * updating
 * 
 * @since 1.0
 * @author MukundBhardwaj
 */
public final class AuthUserDTO {

        public static record GetConciseUserDTO(

                        UUID id,
                        String name) {
                public GetConciseUserDTO(AuthUserDetails authUser) {
                        this(authUser.getId(), authUser.getName());
                }

        }

        public static record GetUserDTO(

                        UUID id,
                        String name,
                        String email,
                        String role,
                        Boolean active) {
                public GetUserDTO(AuthUserDetails authUser) {
                        this(authUser.getId(), authUser.getName(), authUser.getUsername(), authUser.getRole(),
                                        authUser.isEnabled());
                }

        }

        public static record CreateUserDTO(

                        @NotEmpty(message = "name cannot be empty") String name,
                        @NotEmpty(message = "email cannot be empty") String email,
                        @NotEmpty(message = "password cannot be empty") @JsonProperty(access = Access.WRITE_ONLY) String password,
                        @NotNull(message = "active cannot be null") Boolean active) {

        }

        public static record UpdateUserDTO(

                        @NotEmpty(message = "name cannot be empty") String name,
                        @NotEmpty(message = "password cannot be empty") @JsonProperty(access = Access.WRITE_ONLY) String password,
                        @NotNull(message = "active cannot be null") Boolean active) {

        }

        private AuthUserDTO() {
        }
}