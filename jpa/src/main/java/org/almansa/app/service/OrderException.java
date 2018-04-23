package org.almansa.app.service;

public class OrderException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OrderException(String message) {
        super(message);
    }
}