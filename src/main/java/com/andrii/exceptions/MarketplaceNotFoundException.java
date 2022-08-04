package com.andrii.exceptions;

public class MarketplaceNotFoundException extends RuntimeException {
    public MarketplaceNotFoundException(String message) {
        super(message);
    }

    public MarketplaceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
