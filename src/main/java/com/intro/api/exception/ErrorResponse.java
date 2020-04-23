package com.intro.api.exception;

import lombok.*;

@Getter
@Setter
public class ErrorResponse {

    static final String InvalidArgments = "1000";

    static final ErrorResponse InvalidArgumentsError = new ErrorResponse(InvalidArgments, "");

    private String code;
    private String error;

    public ErrorResponse(String code, String error) {
        this.error = error;
        this.code = code;
    }
}