package com.ethoca.shoppingcart.service;

import com.ethoca.shoppingcart.model.CartItemModel;
import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.model.ProductModel;

import java.util.List;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
public interface CartService {

    public List<CartItemModel> getAllCartItems();
    public CartModel getCartModel();
    public String updateCart(long productId, int quantity);
    public boolean removeFromCart(long productId);
    public boolean addToCart(ProductModel productModel, int quantity);
    public boolean confirmOrder();
}
