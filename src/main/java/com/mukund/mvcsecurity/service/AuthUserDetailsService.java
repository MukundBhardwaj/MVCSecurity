package com.mukund.mvcsecurity.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mukund.mvcsecurity.dto.AuthUserDTO;
import com.mukund.mvcsecurity.entity.AuthUser;
import com.mukund.mvcsecurity.exceptionhandler.ResourceNotFoundException;

public interface AuthUserDetailsService extends UserDetailsService {

    public List<AuthUser> findAllUsers();

    public AuthUser createUser(AuthUserDTO.CreateUserDTO authUserDTO);

    public AuthUser getUserByID(UUID id) throws ResourceNotFoundException;

    public AuthUser updateUser(UUID id, AuthUserDTO.UpdateUserDTO authUserDTO) throws ResourceNotFoundException;

    public void deleteUserByID(UUID id) throws ResourceNotFoundException;

}