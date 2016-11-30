package com.ethoca.shoppingcart.service.impl;

import com.ethoca.shoppingcart.dao.CartDao;
import com.ethoca.shoppingcart.domain.CartItem;
import com.ethoca.shoppingcart.model.CartItemModel;
import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.CartService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Service
public class CartServiceImpl implements CartService {

    final static Logger logger = Logger.getLogger(CartServiceImpl.class);

    @Autowired
    CartDao cartDao;

    List<CartItemModel> cartItemModels;
    CartModel cartModel;

    //get the list of all the items in the cart
    @Override
    public List<CartItemModel> getAllCartItems() {

        List<CartItem> cartItems = new ArrayList<CartItem>();
        try
        {
            cartItems = cartDao.findAll();
            if(cartItems != null && !cartItems.isEmpty())
            {
                cartItemModels = new ArrayList<CartItemModel>();
                for(CartItem cartItem : cartItems)
                {
                    CartItemModel cartItemModel = new CartItemModel();

                    /*copy the product in cart to a productModel object,
                    the productModel is set to cartItemModel.productModel*/
                    ProductModel productModelObj = new ProductModel();
                    BeanUtils.copyProperties(productModelObj, cartItem.getProductBook());

                    cartItemModel.setProductModel(productModelObj);
                    cartItemModel.setProductQty(cartItem.getQuantity());
                    cartItemModel.setTotalPrice(cartItem.getTotalPrice());

                    cartItemModels.add(cartItemModel);
                }
            }
            return cartItemModels;
        }catch (Exception e)
        {
            logger.error("EXCEPTION in CartServiceImpl ---> getAllCartItems", e);
            return null;
        }
    }

    //method that returns the cart object to be displayed to the user.
    @Override
    public CartModel getCartModel() {

        cartModel = new CartModel();

        double taxAmount, subTotal = 0.0, totalWithTax;
        try {
            cartItemModels = getAllCartItems();

            for(CartItemModel cartItemModel: cartItemModels)
            {
                subTotal+= cartItemModel.getTotalPrice();
            }

            taxAmount = subTotal * 0.135;       //assuming a tax percent of 13.5
            totalWithTax = taxAmount + subTotal;

            cartModel.setCartItems(cartItemModels);
            cartModel.setSubTotal(subTotal);
            cartModel.setTaxAmount(taxAmount);
            cartModel.setTotalWithTax(totalWithTax);

            return cartModel;
        }catch (Exception e)
        {
            logger.error("EXCEPTION in CartServiceImpl ---> getCartModel", e);
            return null;
        }
    }
}
