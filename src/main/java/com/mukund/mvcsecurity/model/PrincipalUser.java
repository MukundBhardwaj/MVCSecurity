package com.mukund.mvcsecurity.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mukund.mvcsecurity.entitiy.AuthUser;

public class PrincipalUser implements UserDetails {
    private AuthUser authUser;

    public PrincipalUser(AuthUser user) {
        this.authUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(authUser.getRole()));
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authUser.getActive();
    }

    @Override
    public int hashCode() {
        return 31 * 1 + ((authUser == null) ? 0 : authUser.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PrincipalUser other = (PrincipalUser) obj;
        if (authUser == null) {
            if (other.authUser != null)
                return false;
        } else if (!authUser.equals(other.authUser))
            return false;
        return true;
    }

}