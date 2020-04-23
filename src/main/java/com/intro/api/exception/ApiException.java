package com.intro.api.exception;

public class ApiException extends Exception {

    private static final long serialVersionUID = 8159015391473618670L;

    private int status;
    private String code;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, int status, String code) {
        super(message);
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}