package com.ethoca.shoppingcart.service.impl;

import com.ethoca.shoppingcart.dao.UserDao;
import com.ethoca.shoppingcart.domain.User;
import com.ethoca.shoppingcart.model.UserSignUpForm;
import com.ethoca.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public boolean emailExist(String email) {

        if(userDao.findByEmail(email) != null)
            return true;
        else
            return false;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserSignUpForm userSignUpForm)
    {
        final User user = new User();

        user.setEmail(userSignUpForm.getEmail());
        user.setPasswordHash(passwordEncoder.encode(userSignUpForm.getPassword()));
        user.setEnabled(true);

        return userDao.save(user);

    }
}
