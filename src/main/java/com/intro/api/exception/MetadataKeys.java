package com.intro.api.exception;

import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;

public class MetadataKeys {
    public static final Metadata.Key<String> ERROR_CODE = Metadata.Key.of("error_code",
            Metadata.ASCII_STRING_MARSHALLER);

    public static String GetErrorCode(StatusRuntimeException exception) {
        if (exception == null) {
            return "";
        }
        String code = exception.getTrailers().get(ERROR_CODE);
        if (code == null || code.isEmpty()) {
            return exception.getStatus().getCode().name();
        }
        return code;
    }
}