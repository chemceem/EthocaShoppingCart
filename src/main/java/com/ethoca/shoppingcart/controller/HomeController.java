package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chemcee. M. C on 18-11-2016.
 */
@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    List<ProductModel> products;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model){

        products = new ArrayList<ProductModel>();
        try
        {
            products = productService.getAllProducts();
            if(products !=null && !products.isEmpty())
            {
                model.addAttribute("books", products);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(value = "/test")
    public String test()
    {
        return "home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView showUserSignUpPage()
    {
        //return new ModelAndView("signup", "signupform", );
        return null;
    }
}
