package com.ethoca.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chemcee. M. C on 27-11-2016.
 */

@Controller
public class RegisterController {

    @RequestMapping(value = "/register" , method= RequestMethod.GET)
    public String registerCustomer(Model model)
    {
        return "";

    }

}
