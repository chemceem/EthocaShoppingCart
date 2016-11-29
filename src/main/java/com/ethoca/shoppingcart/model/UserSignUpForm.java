package com.ethoca.shoppingcart.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chemcee. M. C on 20-11-2016.
 */
public class UserSignUpForm {

    @NotNull
    @Email
    private String email;

    @NotNull(message = "Password length must be greater than 6 characters.")
    @Size(min=6)
    private String password;

    @NotNull(message = "Password length must be greater than 6 characters.")
    @Size(min=6)
    private String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}