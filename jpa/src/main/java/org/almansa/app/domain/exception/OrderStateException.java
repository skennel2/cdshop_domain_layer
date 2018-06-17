package org.almansa.app.domain.exception;

public class OrderStateException extends RuntimeException{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OrderStateException(Exception exception) {
        super(exception);
    }
    
    public OrderStateException(String message, Exception exception) {
        super(message, exception);
    }
    
    public OrderStateException(String message) {
        super(message);
    }
}
