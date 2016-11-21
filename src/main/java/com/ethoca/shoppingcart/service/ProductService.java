package com.ethoca.shoppingcart.service;

import com.ethoca.shoppingcart.model.ProductModel;

import java.util.List;

/**
 * Created by Chemcee. M. C on 20-11-2016.
 */
public interface ProductService {

    public List<ProductModel> getAllProducts();
    public ProductModel getOne(Long id);
}
