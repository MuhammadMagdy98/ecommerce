package com.example.ecommerce.exception;

public class EcommerceException extends RuntimeException {
    private final EcommerceError error;
    private final transient Object[] args;

    public EcommerceException(EcommerceError error, Object... args) {
        super(error.getMessage());
        this.error = error;
        this.args = args;
    }


    @Override
    public String getMessage() {
        return this.error == null ? super.getMessage() : this.error.getMessage(this.args);
    }
}
