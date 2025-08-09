package com.yourshop.exceptions;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(403, message);
    }
}