package com.epam.esm.dto;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserDto {

    @Pattern(regexp = "^(([A-Z]([A-z]){1,16}))$",message = "should starts with capital letter 1-16 symbols")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,15}$",message = " 3 - 15 symbols")
    private String username;

    @Email(message = "example@example.com")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=\\S+$).{8,}$",
    message = "must contain at least 8 symbols, capital letter, small letter and one digit")
    private String password;

    public UserDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
