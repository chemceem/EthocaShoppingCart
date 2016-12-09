package com.ethoca.shoppingcart.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Chemcee. M. C on 07-12-2016.
 */

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;
    private String orderReferenceNumber;

    @ManyToOne
    private UserAddress userAddress;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrderReferenceNumber() {
        return orderReferenceNumber;
    }

    public void setOrderReferenceNumber(String orderReferenceNumber) { this.orderReferenceNumber = orderReferenceNumber; }
}
