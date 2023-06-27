package com.mukund.mvcsecurity.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.mukund.mvcsecurity.model.AuthUserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "auth_user")
/**
 * Entity class for Authorized user in the application
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class AuthUser implements AuthUserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(min = 3, max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Size(min = 3, max = 50)
    @Column(name = "email", nullable = false, unique = true)
    @JsonProperty(access = Access.READ_ONLY)
    private String username;

    @Size(min = 3, max = 72)
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Size(max = 10)
    @Column(name = "role", nullable = false)
    @JsonProperty(access = Access.READ_ONLY)
    private String role;

    @Column(name = "active", nullable = false)
    private Boolean enabled;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String email) {
        this.username = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
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

    public AuthUser() {
    }

    public AuthUser(UUID id, String name, String email, String password, String role, Boolean active) {
        this.id = id;
        this.name = name;
        this.username = email;
        this.password = password;
        this.role = role;
        this.enabled = active;
    }

    @Override
    public String toString() {
        return "AuthUser [id=" + id + ", name=" + name + ", email=" + username + ", password=" + password + ", role="
                + role + ", active=" + enabled + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AuthUser other = (AuthUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
