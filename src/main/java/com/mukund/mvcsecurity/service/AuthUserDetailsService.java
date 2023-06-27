package com.mukund.mvcsecurity.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mukund.mvcsecurity.dto.AuthUserDTO;
import com.mukund.mvcsecurity.exceptionhandler.ResourceNotFoundException;
import com.mukund.mvcsecurity.model.AuthUserDetails;

public interface AuthUserDetailsService extends UserDetailsService {

    public List<? extends AuthUserDetails> findAllUsers();

    public AuthUserDetails createUser(AuthUserDTO.CreateUserDTO authUserDTO);

    public AuthUserDetails getUserByID(UUID id) throws ResourceNotFoundException;

    public AuthUserDetails updateUser(UUID id, AuthUserDTO.UpdateUserDTO authUserDTO) throws ResourceNotFoundException;

    public void deleteUserByID(UUID id) throws ResourceNotFoundException;

}