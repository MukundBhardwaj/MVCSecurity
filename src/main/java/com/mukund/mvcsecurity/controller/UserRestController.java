package com.mukund.mvcsecurity.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mukund.mvcsecurity.dto.AuthUserDTO;
import com.mukund.mvcsecurity.service.AuthUserDetailsService;
import com.mukund.mvcsecurity.service.implementation.AuthUserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
/**
 * Rest Controller class for user
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class UserRestController {

    private AuthUserDetailsService authUserDetailsService;

    public UserRestController(AuthUserDetailsServiceImpl authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @GetMapping
    public List<AuthUserDTO.GetConciseUserDTO> getUsers() {
        return authUserDetailsService.findAllUsers().stream().map(AuthUserDTO.GetConciseUserDTO::new).toList();
    }

    @PostMapping
    public AuthUserDTO.GetUserDTO createUser(@Valid @RequestBody AuthUserDTO.CreateUserDTO authuserDTO) {
        return new AuthUserDTO.GetUserDTO(authUserDetailsService.createUser(authuserDTO));
    }

    @GetMapping("/{id}")
    public AuthUserDTO.GetUserDTO getUser(@PathVariable(name = "id", required = true) UUID id) {
        return new AuthUserDTO.GetUserDTO(authUserDetailsService.getUserByID(id));
    }

    @PutMapping("/{id}")
    public AuthUserDTO.GetUserDTO updateUser(@PathVariable(name = "id", required = true) UUID id,
            @Valid @RequestBody AuthUserDTO.UpdateUserDTO authUserDTO) {
        return new AuthUserDTO.GetUserDTO(authUserDetailsService.updateUser(id, authUserDTO));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id", required = true) UUID id) {
        authUserDetailsService.deleteUserByID(id);
    }
}