package com.escl.citi.data.dto;

import com.escl.citi.storage.CroppedImageParams;
import com.escl.citi.validation.validator.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;

public class DealerDto implements SystemUserDto {

    private Integer id;

    @NotEmpty
    @Email
    @Size(max = 250)
    private String email;

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

    @ValidPassword
    private String password;

    private String passwordConfirm;

    @Size(min = 9, max = 13, message = "Numer powinien posiadać format np. +48600700800")
    private String mobile;

    @Size(min = 5, max = 250)
    private String icon;

    private MultipartFile file;

    private CroppedImageParams imageParams;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public CroppedImageParams getImageParams() {
        return imageParams;
    }

    public void setImageParams(CroppedImageParams imageParams) {
        this.imageParams = imageParams;
    }
}
