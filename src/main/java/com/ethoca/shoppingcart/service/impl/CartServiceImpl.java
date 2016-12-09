package com.ethoca.shoppingcart.service.impl;

import com.ethoca.shoppingcart.dao.CartDao;
import com.ethoca.shoppingcart.dao.ProductDao;
import com.ethoca.shoppingcart.dao.UserDao;
import com.ethoca.shoppingcart.domain.CartItem;
import com.ethoca.shoppingcart.domain.OrderDetails;
import com.ethoca.shoppingcart.domain.ProductBook;

import com.ethoca.shoppingcart.domain.User;
import com.ethoca.shoppingcart.model.CartItemModel;
import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.CartService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Service
public class CartServiceImpl implements CartService {

    final static Logger logger = Logger.getLogger(CartServiceImpl.class);

    /* This format is used to append the decimal digits to two digits. */
    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @Autowired
    CartDao cartDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    CartModel cartModel;
    List<CartItemModel> cartItemModels;
    User currentUser;

    /**get the list of all the items in the cart */
    @Override
    public List<CartItemModel> getAllCartItems() {

        List<CartItem> cartItems;
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
            return cartItemModels;
            } else {
                if(cartItemModels!=null && !cartItemModels.isEmpty())
                    cartItemModels.removeAll(cartItemModels);
                return null;
            }
        }catch (Exception e)
        {
            logger.error("EXCEPTION in CartServiceImpl ---> getAllCartItems", e);
            return null;
        }
    }

    /**method that returns the cart object to be displayed to the user. */
    @Override
    public CartModel getCartModel() {

        cartModel = new CartModel();
        double taxAmount, subTotal = 0.0, totalWithTax;

        try {
            getAllCartItems();
            if(cartItemModels != null && !cartItemModels.isEmpty())
            {
                for(CartItemModel cartItemModel: cartItemModels)
                {
                    subTotal+= cartItemModel.getTotalPrice();
                }

                taxAmount = subTotal * 0.135;       //assuming a tax percent of 13.5
                totalWithTax = taxAmount + subTotal;

                cartModel.setCartItems(cartItemModels);
                cartModel.setSubTotal(Double.parseDouble(decimalFormat.format(subTotal)));
                cartModel.setTaxAmount(Double.parseDouble(decimalFormat.format(taxAmount)));
                cartModel.setTotalWithTax(Double.parseDouble(decimalFormat.format(totalWithTax)));

                return cartModel;
            } else
            {
                return null;
            }
        }catch (Exception e)
        {
            logger.error("EXCEPTION in CartServiceImpl ---> getCartModel", e);
            return null;
        }
    }

    /**
     * Service layer, add product to cart.
     * Returns true if product was successfully added, else return false
     */
    @Override
    public boolean addToCart(ProductModel productModel, int quantity)
    {
        logger.info("inside CartServiceImpl ---> addToCart()");

        CartItem cartItem;
        ProductBook productBook;

        try {
            productBook = productDao.findById(productModel.getId());
            if(productBook != null)
            {
                currentUser = getCurrentUser();
                if(currentUser != null)
                {
                    cartItem = cartDao.findByProductBookAndUser(productBook, currentUser);

                    if(cartItem != null)
                        cartItem.setUser(currentUser);
                    else {
                        cartItem = new CartItem();
                        cartItem.setUser(currentUser);
                    }
                } else {
                    //check if the product already exist in the cart table
                    cartItem = cartDao.findByProductBook(productBook);
                }

                /*
                 * if product already exist in the cart table,
                 * the quantity is updated by adding new quantity to existing quantity                 *
                 */
                if(cartItem != null)
                {
                    int updatedQuantity = quantity + cartItem.getQuantity();
                    double price = updatedQuantity * productBook.getPrice();

                    cartItem.setQuantity(updatedQuantity);
                    cartItem.setTotalPrice(price);
                    cartItem.setDate(new Date());

                    cartDao.save(cartItem);
                    return true;
                } else {
                    double price = quantity * productBook.getPrice();

                    cartItem = new CartItem();
                    cartItem.setProductBook(productBook);
                    cartItem.setQuantity(quantity);
                    cartItem.setTotalPrice(price);
                    cartItem.setDate(new Date());

                    cartDao.save(cartItem);
                    return true;
                }
            } else
                return false;
        }catch (Exception e) {
            logger.error("Exception in CartServiceImpl ---> addToCart", e);
            return false;
        }
    }

    /**
     * updates the quantity of an item in the cart.
     * returns the new subTotal as string.
     *
     * Null is returned if an exception occurs.
     */
    @Override
    public String updateCart(long productId, int quantity)
    {
        logger.info("Updating cartItem. CartServiceImpl ----> updateCart ");

        double subTotal = 0.0;
        ProductBook productBook;
        CartItem cartItem;
        List<CartItem> cartItems;

        try {
            productBook = productDao.findById(productId);
            if(productBook != null)
            {
                cartItem = cartDao.findByProductBook(productBook);

                if(cartItem != null)
                {
                    double price = quantity * productBook.getPrice();

                    cartItem.setQuantity(quantity);
                    cartItem.setTotalPrice(price);
                    cartItem.setDate(new Date());

                    cartDao.save(cartItem);

                    //looping through all the items in the cart to find the new total cost.
                    cartItems = cartDao.findAll();
                    for (CartItem cartItemTemp : cartItems) {
                        subTotal+= cartItemTemp.getTotalPrice();
                    }

                    //round to two decimal places
                    subTotal = Double.parseDouble(decimalFormat.format(subTotal));

                    return String.valueOf(subTotal);

                } else {
                    return null;
                }
            }
            else
                return null;
        }catch (Exception e) {
            logger.error("Exception in CartServiceImpl ---> updateCart ", e);
            return null;
        }
    }

    /**
     * Service layer method to remove an item from the cart.
     * The productid is passed as parameter.
     * Returns a boolean value of True if success
     * */
    @Override
    public boolean removeFromCart(long productId)
    {
        CartItem cartItem;
        cartModel = new CartModel();
        ProductBook productBook;
        try {
            productBook = productDao.findById(productId);
            if(productBook != null)
            {
                //check if the product exist in the cart table
                cartItem = cartDao.findByProductBook(productBook);

                /*
                 * if product already exist in the cart table, delete the entry from the table
                 */
                if(cartItem != null)
                {
                    cartDao.delete(cartItem);
                    cartModel = getCartModel();
                    return true;
                } else
                    return false;
            } else
                return false;

        }catch(Exception e) {
            logger.error("Exception in CartServiceImpl ---> removeFromCart ", e);
            return false;
        }
    }

    /** Order confirmation.
     * Pending
     * */
    @Override
    public boolean confirmOrder()
    {
        List<OrderDetails> orderDetails;
        try {

            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private User getCurrentUser()
    {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken))
        {
            org.springframework.security.core.userdetails.User loggedInUser = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            User user = userDao.findByEmail(loggedInUser.getUsername());

            return user;
        } else
            return null;
    }
}
