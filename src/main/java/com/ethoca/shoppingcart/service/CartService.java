package com.ethoca.shoppingcart.service;

import com.ethoca.shoppingcart.model.CartItemModel;
import com.ethoca.shoppingcart.model.CartModel;

import java.util.List;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
public interface CartService {

    public List<CartItemModel> getAllCartItems();
    public CartModel getCartModel();
}
