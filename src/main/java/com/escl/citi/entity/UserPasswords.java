package com.escl.citi.entity;

import javax.persistence.Embeddable;
@Embeddable
public class UserPasswords {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
