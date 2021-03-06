package com.ethoca.shoppingcart.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Component
//@Scope(value = "session" , proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartModel
{
    List<CartItemModel> cartItems;
    double taxAmount;
    double totalWithTax;
    double subTotal;

    public CartModel() {
    }

    public CartModel(List<CartItemModel> cartItems, double taxAmount, double totalWithTax, double subTotal) {
        this.cartItems = cartItems;
        this.taxAmount = taxAmount;
        this.totalWithTax = totalWithTax;
        this.subTotal = subTotal;
    }

    public List<CartItemModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemModel> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalWithTax() {
        return totalWithTax;
    }

    public void setTotalWithTax(double totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}