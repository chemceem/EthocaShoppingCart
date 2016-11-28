
package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.ProductService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Chemcee. M. C on 21-11-2016.
 */

@Controller
@RequestMapping("/book")
public class ProductController {

    final static Logger logger = Logger.getLogger(ProductController.class);

    ProductModel book;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProductById(@PathVariable long id, Model model){

        try
        {
            logger.log(Level.INFO, "Received request to fetch product id="+id+" ---> ProductController --> ProductModel()");
            book = productService.getOne(id);
            if(book != null)
                model.addAttribute("book", book);
            else
                model.addAttribute("error", "Error while fetching item.");
        }catch (Exception e)
        {
            logger.error("EXCEPTION", e);
            return null;
        }

        return "productDescription";
    }

}

