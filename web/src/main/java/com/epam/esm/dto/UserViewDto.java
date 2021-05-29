package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * User dto to view on pages
 */
public class UserViewDto extends RepresentationModel<UserViewDto> {

    private Integer id;

    private String firstName;

    private String username;

    private String email;

    /**
     * Instantiates a new User view dto.
     */
    public UserViewDto() {
    }

    /**
     * Instantiates a new User view dto.
     *
     * @param id        the id
     * @param firstName the first name
     * @param username  the username
     * @param email     the email
     */
    public UserViewDto(Integer id, String firstName, String username, String email) {
        this.id = id;
        this.firstName = firstName;
        this.username = username;
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
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
}

