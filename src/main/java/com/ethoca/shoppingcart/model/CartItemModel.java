package com.ethoca.shoppingcart.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartItemModel
{
    ProductModel productModel;
    int productQty;
    double totalPrice;

    public CartItemModel() {
    }

    public CartItemModel(ProductModel productModel, int productQty, double totalPrice) {
        this.productModel = productModel;
        this.productQty = productQty;
        this.totalPrice = totalPrice;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
