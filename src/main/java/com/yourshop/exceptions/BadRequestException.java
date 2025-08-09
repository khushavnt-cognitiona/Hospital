package com.yourshop.exceptions;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(400, message);
    }
}