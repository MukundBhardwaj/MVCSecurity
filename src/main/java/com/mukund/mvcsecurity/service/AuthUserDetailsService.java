package com.mukund.mvcsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mukund.mvcsecurity.dao.AuthUserRepository;
import com.mukund.mvcsecurity.model.PrincipalUser;

@Service
/**
 * Service class for AuthUser
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class AuthUserDetailsService implements UserDetailsService {

    private AuthUserRepository authUserRepository;

    public AuthUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new PrincipalUser(authUserRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " doesn't exist")));
    }

}
