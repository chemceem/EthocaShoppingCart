package com.ethoca.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Chemcee. M. C on 18-11-2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){

        return "index";
    }

    @RequestMapping("/test")
    public String test()
    {
        System.out.println("inside the controller");
        return "home";
    }
}
