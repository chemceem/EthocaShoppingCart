package com.ethoca.shoppingcart.utils;

import com.ethoca.shoppingcart.model.UserSignUpForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>
{
    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {

    }

    @Override
    public boolean isValid(final Object object, final ConstraintValidatorContext context) {


        final UserSignUpForm user = (UserSignUpForm)object;
        System.out.println("inside password matches "+ user.getPassword().equals(user.getConfirmPassword()));
        return user.getPassword().equals(user.getConfirmPassword());
    }

}
