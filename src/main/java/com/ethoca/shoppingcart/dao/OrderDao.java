package com.ethoca.shoppingcart.dao;

import com.ethoca.shoppingcart.domain.Order;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
public interface OrderDao extends CrudRepository<Order, Long> {
}
