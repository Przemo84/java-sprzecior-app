package com.nordgeo.data.dto;

import com.nordgeo.validators.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto implements SystemUserDto {

    private Integer id;

    @NotBlank
    @Size(min = 2, max = 250)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 250)
    private String lastName;

    @NotBlank
    @NotEmpty
    @Size(min = 3, max = 250)
    private String username;

    @Email
    @NotEmpty
    @Size(max = 250)
    private String email;

    private String password;

    private String passwordConfirm;


    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public String getStrId() {
        return id.toString();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
