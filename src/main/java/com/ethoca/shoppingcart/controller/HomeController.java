package com.ethoca.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chemcee. M. C on 08-12-2016.
 */
@Controller
@RequestMapping(value={"/index","/"})
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String returnHome(Model model)
    {
        return "index";
    }

}
