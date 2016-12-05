package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.CartModel;
import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.ProductService;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chemcee. M. C on 18-11-2016.
 */

@RestController
public class HomeController {

    @Autowired
    ProductService productService;

    ProductModel book;

    List<ProductModel> products;

    //@CrossOrigin(origins = "http://localhost:8000")
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public @ResponseBody List<ProductModel> GetAllProducts(Model model){

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
        return products;
    }
}
