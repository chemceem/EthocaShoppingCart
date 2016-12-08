package com.ethoca.shoppingcart.domain;

import javax.persistence.*;

/**
 * Created by Chemcee. M. C on 07-12-2016.
 */
@Entity
@Table(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ProductBook productBook;

    private int quantity;

    @ManyToOne
    private Order order;

    private int status;

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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
