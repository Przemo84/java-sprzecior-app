package com.escl.citi.data;

import com.escl.citi.validation.validator.ValidPassword;

public class UserPasswordDto {

    private String currentPassword;

    @ValidPassword
    private String newPassword;

    private String passwordConfirm;

    public UserPasswordDto() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String password) {
        this.currentPassword = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}