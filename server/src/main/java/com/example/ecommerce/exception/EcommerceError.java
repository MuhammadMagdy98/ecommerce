package com.example.ecommerce.exception;

import lombok.Getter;

import java.text.MessageFormat;

@Getter
public enum EcommerceError {

    USER_ALREADY_EXIST(400, "This email is already in use"),
    INVALID_CREDENTIALS(400, "Invalid Credentials"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "User not found");



    private final String message;
    private final int status;
    EcommerceError(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage(Object... args) {
        return new MessageFormat(this.message).format(args);
    }
}
