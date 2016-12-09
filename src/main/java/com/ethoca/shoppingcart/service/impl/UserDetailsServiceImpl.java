package com.ethoca.shoppingcart.service.impl;

import com.ethoca.shoppingcart.dao.UserDao;
import com.ethoca.shoppingcart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService
{

    @Autowired
    UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDao.findByEmail(email);

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
    }

}
