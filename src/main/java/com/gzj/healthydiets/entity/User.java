package com.gzj.healthydiets.entity;

import org.springframework.stereotype.Component;

@Component
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer authority;

    public User() {
    }

    public User(Integer id, String username, String password, String email, Integer authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", authority=" + authority +
                '}';
    }
}
