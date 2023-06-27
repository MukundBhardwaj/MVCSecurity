package com.mukund.mvcsecurity.service.implementation;

import java.util.List;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mukund.mvcsecurity.dao.AuthUserRepository;
import com.mukund.mvcsecurity.dto.AuthUserDTO;
import com.mukund.mvcsecurity.entity.AuthUser;
import com.mukund.mvcsecurity.exceptionhandler.ResourceNotFoundException;
import com.mukund.mvcsecurity.model.AuthUserDetails;
import com.mukund.mvcsecurity.service.AuthUserDetailsService;

@Service
/**
 * Service class implementation for {@code AuthUserDetailsService}
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class AuthUserDetailsServiceImpl implements AuthUserDetailsService {

    private AuthUserRepository authUserRepository;

    public AuthUserDetailsServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist"));
    }

    public AuthUserDetails createUser(AuthUserDTO.CreateUserDTO authUserDTO) {
        return authUserRepository
                .save(new AuthUser(null, authUserDTO.name(), authUserDTO.email(), authUserDTO.password(), "ROLE_USER",
                        authUserDTO.active()));
    }

    public List<? extends AuthUserDetails> findAllUsers() {
        return authUserRepository.findAll();
    }

    public AuthUserDetails getUserByID(UUID id) throws ResourceNotFoundException {
        return authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + id + " not found"));
    }

    public AuthUserDetails updateUser(UUID id, AuthUserDTO.UpdateUserDTO authUserDTO) throws ResourceNotFoundException {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + id + " not found"));
        authUser.setName(authUserDTO.name());
        authUser.setPassword(authUserDTO.password());
        authUser.setEnabled(authUserDTO.active());
        return authUserRepository.save(authUser);
    }

    public void deleteUserByID(UUID id) throws ResourceNotFoundException {
        if (authUserRepository.existsById(id))
            authUserRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("User with ID - " + id + " not found");
    }

}