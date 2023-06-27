package com.mukund.mvcsecurity.model;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthUserDetails extends UserDetails {

    public UUID getId();

    public void setId(UUID id);

    public String getName();

    public void setName(String name);

    @Override
    public String getUsername();

    public void setUsername(String email);

    public String getPassword();

    public void setPassword(String password);

    public String getRole();

    public void setRole(String role);

    @Override
    public boolean isEnabled();

    public void setEnabled(Boolean enabled);

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities();

    @Override
    public boolean isAccountNonExpired();

    @Override
    public boolean isAccountNonLocked();

    @Override
    public boolean isCredentialsNonExpired();

}