package org.almansa.app.service.exception;

import org.almansa.app.domain.exception.RemainingStockException;

public class OrderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderException(RemainingStockException e) {
        super(e);
    }
    
    public OrderException(RemainingStockException e, String message) {
        super(message, e);
    }
}