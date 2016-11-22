package com.ethoca.shoppingcart.controller;

import com.ethoca.shoppingcart.domain.ProductBook;
import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody ProductModel getProductById(@PathVariable long id){

        try
        {
            book = productService.getOne(id);
            if(book != null)
                return book;
            else
                return null;

        }catch (Exception e)
        {
            logger.error("EXCEPTION", e);
            return null;
        }
    }

}
