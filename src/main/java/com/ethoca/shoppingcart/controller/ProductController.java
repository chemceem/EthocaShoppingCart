
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chemcee. M. C on 21-11-2016.
 */

@Controller
@RequestMapping("/products")
public class ProductController {

    final static Logger logger = Logger.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    /*Returns a single product based on the id*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProductById(@PathVariable long id, Model model){

        ProductModel book;
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

    /* Returns the list of all the products present in the database*/
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<ProductModel> GetAllProducts(Model model){

        logger.log(Level.INFO, "Request to fetch all products.");

        List<ProductModel> products = new ArrayList<ProductModel>();
        try
        {
            products = productService.getAllProducts();
            if(products !=null && !products.isEmpty())
            {
                return products;
            }
            else
                return null;
        }catch (Exception e)
        {
            logger.error("Error while fetching all products. ProductController ----. GetAllProducts");
            e.printStackTrace();
            return null;
        }
    }

}

