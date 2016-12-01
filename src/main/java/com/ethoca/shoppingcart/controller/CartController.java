package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.AddressForm;
import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.service.CartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Controller
public class CartController {

    final static Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    CartService cartService;
    CartModel cartModel;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    private String returnCurrentCart(Model model)
    {
        CartModel cartModel;
        try {
            cartModel = cartService.getCartModel();
            //cartModel = null;
            if(cartModel != null )
                model.addAttribute("cartModel", cartModel);
            else
                model.addAttribute("cartError", "Your shopping cart is empty.");
        }catch (Exception e)
        {
            e.printStackTrace();
            model.addAttribute("error", "Error while processing cart. Please try again later.");
        }
        return "cart";
    }

    @RequestMapping(value="/cart/checkout", method = RequestMethod.GET)
    private String showCheckout(Model model)
    {
        CartModel cartModel;

        //check if the logged in user has an address already,
        //if address, populate the address form with existing details
        //to do item
        AddressForm addressForm = new AddressForm();
        addressForm.setAddress("temp address");
        addressForm.setFirstName("chemcee");
        addressForm.setLastName("cherian");
        addressForm.setCountry("country");
        try {
            cartModel = cartService.getCartModel();
            model.addAttribute("addressForm", addressForm);
            model.addAttribute("cartModel", cartModel);

        }catch (Exception e){
            logger.error("EXCEPTION in CartController ---> showCheckout", e);
            model.addAttribute("checkoutError", "Error while processing. Try again later.");
        }
        return "checkout";
    }

    @RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
    private String confirmCheckout(@Valid @ModelAttribute("addressForm") AddressForm addressForm, BindingResult result, Model model)
    {
        if(result.hasErrors())
        {
            if(cartModel != null )
                model.addAttribute("cartModel", cartModel);
            else
                model.addAttribute("cartError", "Your shopping cart is empty.");

            return "checkout";
        }

        return "redirect:/cart/checkout";
    }
}
