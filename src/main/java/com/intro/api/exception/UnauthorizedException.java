package com.intro.api.exception;

public class UnauthorizedException extends Exception {

    private static final long serialVersionUID = -8283051629465565302L;

    public UnauthorizedException() {
        super("Unauthorized");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}