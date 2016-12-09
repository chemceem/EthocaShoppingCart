package com.ethoca.shoppingcart.utils;

import com.ethoca.shoppingcart.model.UserSignUpForm;

import com.ethoca.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */

@Component
public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserSignUpForm.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors)
    {
        UserSignUpForm userSignUpForm = (UserSignUpForm) object;

        if(userService.emailExist(userSignUpForm.getEmail())){
            //errors.rejectValue("email", "Duplicate.userForm.username",);
            errors.rejectValue("email", "Duplicate.userForm.username","An account exist with the email provided.");
        }

        if(!userSignUpForm.getPassword().equals(userSignUpForm.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.differentPasswords","Passwords do not match.");
        }
    }
}
