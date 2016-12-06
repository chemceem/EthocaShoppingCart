package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.AddressForm;
import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.CartService;
import com.ethoca.shoppingcart.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Controller
@Scope("request")
public class CartController {

    final static Logger logger = Logger.getLogger(CartController.class);

    @Autowired
    CartService cartService;
    CartModel cartModel;

    @Autowired
    ProductService productService;

    HttpServletRequest request;
    HttpSession session;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    private String returnCurrentCart(Model model)
    {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        session = request.getSession(true);

        try {
            cartModel = cartService.getCartModel();
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

    /*
      method that adds new items to the cart.
    */
    @RequestMapping(value = "/cart/add/{bookid}/{quantity}", method = RequestMethod.POST)
    private @ResponseBody String addItemToCart(@PathVariable long bookid, @PathVariable int quantity, Model model)
    {
        ProductModel product;
        try {
            product = productService.getOne(bookid);
            if(product != null)
            {
                if(cartService.addToCart(product, quantity))
                {
                    return quantity+" Items Are Added To Cart.";
                } else
                {
                    return "Item could not be added to cart. Try again later.";
                }
            } else
            {
                return "Error while processing. Try again later.";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "Item could not be added to cart. Try again later.";
        }
    }

    @RequestMapping(value = "/cart/remove/{bookid}", method = RequestMethod.POST)
    private @ResponseBody String removeItemsFromCart(Model model)
    {
        try {

            return "";
        }catch (Exception e) {
            e.printStackTrace();
            return "Error while removing item. Try again later";
        }
    }

     /*  Continue to checkout page.
      *  The user is shown the final page before confirmation.
      *  Allows the user to enter an address
      */
    @RequestMapping(value="/cart/checkout", method = RequestMethod.GET)
    private String showCheckout(Model model)
    {
        //check if the logged in user has an address already,
        //if address, populate the address form with existing details
        //to do item
        AddressForm addressForm = new AddressForm();
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

    /*
        Confirming the order at the checkout
     */
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
