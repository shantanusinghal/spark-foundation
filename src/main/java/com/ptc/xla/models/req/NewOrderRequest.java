package com.ptc.xla.models.req;

/**
 * Created by ssinghal
 * Created on 27-Jan-2016
 * If you refactor this code, remember: Code so clean you could eat off it!
 */
public class NewOrderRequest {

    private int orderNumber;
    private String orderContact;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderContact() {
        return orderContact;
    }

    public void setOrderContact(String orderContact) {
        this.orderContact = orderContact;
    }


}
