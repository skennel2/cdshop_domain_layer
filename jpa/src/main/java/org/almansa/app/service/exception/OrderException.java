package org.almansa.app.service.exception;

public class OrderException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public OrderException(Exception e) {
        super(e);
    }
    
    public OrderException(String message, Exception e) {
        super(message, e);
    }
}