package com.ethoca.shoppingcart.service.impl;


import com.ethoca.shoppingcart.dao.UserDao;
import com.ethoca.shoppingcart.domain.User;
import com.ethoca.shoppingcart.model.Role;
import com.ethoca.shoppingcart.model.UserSignUpForm;
import com.ethoca.shoppingcart.service.UserService;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    final static Logger logger = Logger.getLogger(UserServiceImpl.class);

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
        user.setRole(Role.USER);

        return userDao.save(user);

    }

    /**
       implementation of method that logins a user automatically after he/she registers with the system.
     */
    @Override
    public void autoLogin(String username, String password)
    {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if(usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            logger.info("Autologin --> "+username+" --> Successful");
        }
    }

}
