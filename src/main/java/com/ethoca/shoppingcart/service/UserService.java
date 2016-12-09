package com.ethoca.shoppingcart.service;

import com.ethoca.shoppingcart.domain.User;
import com.ethoca.shoppingcart.model.UserSignUpForm;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
public interface UserService {

    public boolean emailExist(String email);
    public User registerNewUserAccount(UserSignUpForm user);

}
