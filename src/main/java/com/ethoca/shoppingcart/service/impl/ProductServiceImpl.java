package com.ethoca.shoppingcart.service.impl;

import com.ethoca.shoppingcart.dao.ProductDao;
import com.ethoca.shoppingcart.domain.ProductBook;
import com.ethoca.shoppingcart.model.ProductModel;
import com.ethoca.shoppingcart.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chemcee. M. C on 20-11-2016.
 */
@Service
public class ProductServiceImpl implements ProductService {

    final static Logger logger = Logger.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductDao productDao;

    List<ProductModel> productModels;
    ProductModel productModel;

    /*
    Service layer, method to get all products from the database
     */
    @Override
    public List<ProductModel> getAllProducts() {

        List<ProductBook> productBooks = new ArrayList<ProductBook>();
        try {
            productBooks = productDao.findAllByOrderByTitleAsc();
            if(productBooks != null && !productBooks.isEmpty())
            {
                productModels = new ArrayList<ProductModel>();
                for (ProductBook productBook : productBooks) {

                    ProductModel productModelObj = new ProductModel();
                    BeanUtils.copyProperties(productModelObj, productBook);

                    productModels.add(productModelObj);
                }
            }
            return productModels;

        }catch (Exception e)
        {
            logger.error("EXCEPTION in ProductServiceImpl ---> getAllProducts", e);
            return null;
        }
    }

    //method to get a single product based on the id
    @Override
    public ProductModel getOne(Long id) {

        ProductBook productBook;
        try
        {
            logger.log(Level.INFO, "Fetching product with id "+id +"--> getOne, productServiceImpl");
            productBook = productDao.findById(id);
            if(productBook != null)
            {
                productModel = new ProductModel();
                //Beanutils class is used to copy properties of entityObject to model object
                BeanUtils.copyProperties(productModel, productBook);
                return productModel;
            } else
                return null;

        }catch (Exception e) {
            logger.error(Level.ERROR+ ": EXCEPTION in ProductServiceImpl --> getOne", e);
            return null;
        }
    }
}
