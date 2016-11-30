package com.ethoca.shoppingcart.dao;

import com.ethoca.shoppingcart.domain.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
public interface CartDao extends CrudRepository<CartItem, Long>{

    public List<CartItem> findAll();
}
