package com.mukund.mvcsecurity.entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class AuthUser {
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
    private String email;

    @Size(min = 3, max = 72)
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Size(max = 10)
    @Column(name = "role", nullable = false)
    @JsonProperty(access = Access.READ_ONLY)
    private String role;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public AuthUser() {
    }

    public AuthUser(UUID id, String name, String email, String password, String role, Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    @Override
    public String toString() {
        return "AuthUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
                + role + ", active=" + active + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        return true;
    }

}
