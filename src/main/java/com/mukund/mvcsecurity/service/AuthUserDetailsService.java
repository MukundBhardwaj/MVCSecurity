package com.mukund.mvcsecurity.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mukund.mvcsecurity.dto.AuthUserDTO;
import com.mukund.mvcsecurity.exceptionhandler.ResourceNotFoundException;
import com.mukund.mvcsecurity.model.AuthUserDetails;

/**
 * Service interface for {@code AuthUserDetails}
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public interface AuthUserDetailsService extends UserDetailsService {

    @Override
    /**
     * Method to get user by username
     * 
     * @param username username for user to load
     * @return {@code UserDetails}
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    /**
     * Method to get all the list of users
     * 
     * @return List<? extends AuthUserDetails>
     */
    public List<? extends AuthUserDetails> findAllUsers();

    /**
     * Method to create user
     * 
     * @param authUserDTO {@code AuthUserDTO.createUserDTO}
     * @return {@code AuthUserDetails}
     */
    public AuthUserDetails createUser(AuthUserDTO.CreateUserDTO authUserDTO);

    /**
     * Method to get user by user ID
     * 
     * @param id UUID user ID
     * @return {@code AuthUserDetails}
     * @throws ResourceNotFoundException
     */
    public AuthUserDetails getUserByID(UUID id) throws ResourceNotFoundException;

    /**
     * Method to update user
     * 
     * @param id          UUID user ID
     * @param authUserDTO {@code AuthUserDTO.UpdateUserDTO}
     * @return {@code AuthUserDetails}
     * @throws ResourceNotFoundException
     */
    public AuthUserDetails updateUser(UUID id, AuthUserDTO.UpdateUserDTO authUserDTO) throws ResourceNotFoundException;

    /**
     * Method to delete user using ID
     * 
     * @param id UUID user ID
     * @throws ResourceNotFoundException
     */
    public void deleteUserByID(UUID id) throws ResourceNotFoundException;

}