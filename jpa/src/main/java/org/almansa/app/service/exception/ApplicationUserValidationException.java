package org.almansa.app.service.exception;

public class ApplicationUserValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ApplicationUserValidationException(String message, Exception exception) {
        super(message, exception);
    }
    
    public ApplicationUserValidationException(Exception exception) {
        super(exception);
    }

    public ApplicationUserValidationException(String message) {
        super(message);
    }
}