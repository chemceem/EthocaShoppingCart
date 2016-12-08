package com.ethoca.shoppingcart.dao;

import com.ethoca.shoppingcart.domain.UserAddress;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
public interface AddressDao extends CrudRepository<UserAddress, Long> {
}
