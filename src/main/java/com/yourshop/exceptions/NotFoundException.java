package com.yourshop.exceptions;

public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(404, message);
    }
}