package com.mukund.mvcsecurity.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mukund.mvcsecurity.entity.AuthUser;

/**
 * Repository interface for AuthUser
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {

    /**
     * Method to find record by username
     * 
     * @param username {@code String} username of the user
     * @return {@code Optional<AuthUser>} AuthUser Object
     */
    public Optional<AuthUser> findByUsername(String username);

}