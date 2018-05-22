package org.almansa.app.service.exception;

public class OrderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderException(String message) {
        super(message);
    }
}