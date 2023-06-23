package com.mukund.mvcsecurity.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mukund.mvcsecurity.dao.AuthUserRepository;
import com.mukund.mvcsecurity.dto.AuthUserDTO;
import com.mukund.mvcsecurity.entitiy.AuthUser;
import com.mukund.mvcsecurity.exceptionhandler.ResourceNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
/**
 * Rest Controller class for user
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class UserRestController {

    private AuthUserRepository authUserRepository;

    public UserRestController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @GetMapping
    public List<AuthUser> getUsers() {
        return authUserRepository.findAll();
    }

    @PostMapping
    public AuthUser createUser(@Valid @RequestBody AuthUserDTO authuserDTO) {
        return authUserRepository
                .save(new AuthUser(null, authuserDTO.name(), authuserDTO.email(), authuserDTO.password(), "ROLE_USER",
                        authuserDTO.active()));
    }

    @GetMapping("{id}")
    public AuthUser getUser(@PathVariable(name = "id", required = true) UUID id) {
        return authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + id + " not found"));
    }

    @PutMapping("{id}")
    public AuthUser updateUser(@PathVariable(name = "id", required = true) UUID id,
            @Valid @RequestBody AuthUserDTO authUserDTO) {
        AuthUser authUser = authUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID - " + id + " not found"));
        authUser.setEmail(authUserDTO.email());
        authUser.setName(authUserDTO.name());
        authUser.setPassword(authUserDTO.password());
        return authUserRepository.save(authUser);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(name = "id", required = true) UUID id) {
        if (authUserRepository.existsById(id))
            authUserRepository.deleteById(id);
        else
            throw new ResourceNotFoundException("User with ID - " + id + " not found");
    }
}