package com.epam.esm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * User DTO to sign up
 */
public class UserSignUpDto {

    @Pattern(regexp = "^(([A-Z]([A-z]){1,16}))$", message = "should starts with capital letter 1-16 symbols")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,15}$", message = " 3 - 15 symbols")
    private String username;

    @Email(message = "example@example.com")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=\\S+$).{8,}$",
            message = "must contain at least 8 symbols, capital letter, small letter and one digit")
    private String password;

    /**
     * Instantiates a new User sign up dto.
     */
    public UserSignUpDto() {
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
