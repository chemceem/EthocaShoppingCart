package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    private String returnCurrentCart(Model model)
    {
        CartModel cartModel = new CartModel();
        try {
            cartModel = cartService.getCartModel();
            model.addAttribute("cartModel", cartModel);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "cart";
    }

}
