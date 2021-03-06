package com.ethoca.shoppingcart.dao;

import com.ethoca.shoppingcart.domain.ProductBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Chemcee. M. C on 20-11-2016.
 */
public interface ProductDao extends CrudRepository<ProductBook, Long>{

    public List<ProductBook> findAllByOrderByTitleAsc();
    public ProductBook findById(Long id);
    public ProductBook save(ProductBook book);
}
