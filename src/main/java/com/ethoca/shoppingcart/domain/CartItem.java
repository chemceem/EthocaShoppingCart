package com.ethoca.shoppingcart.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Chemcee. M. C on 29-11-2016.
 */
@Entity
@Table(name="cartitem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductBook productBook;
    private int quantity;
    private double totalPrice;
    private Date date;

    @ManyToOne
    private User user;

    public CartItem(){

    }

    public CartItem(ProductBook productBook, int quantity, double totalPrice, Date date) {
        this.productBook = productBook;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductBook getProductBook() {
        return productBook;
    }

    public void setProductBook(ProductBook productBook) {
        this.productBook = productBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
