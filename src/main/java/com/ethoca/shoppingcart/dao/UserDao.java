package com.ethoca.shoppingcart.dao;

import com.ethoca.shoppingcart.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
