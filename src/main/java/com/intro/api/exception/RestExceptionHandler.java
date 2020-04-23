package com.intro.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unauthorizedExceptionHandler(HttpServletRequest request, UnauthorizedException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse validationExceptionHandler(HttpServletRequest request,
            MethodArgumentNotValidException exception) {
        return new ErrorResponse("1000", exception.getMessage());
    }

    @ExceptionHandler(value = StatusRuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse grpcExceptionHandler(HttpServletRequest request, StatusRuntimeException exception) {
        log.error("Grpc error:", exception);
        Status status = exception.getStatus();
        ErrorResponse response = new ErrorResponse(MetadataKeys.GetErrorCode(exception), status.getDescription());
        return response;
    }

    @ExceptionHandler(value = ApiException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse apiExceptionHandler(HttpServletRequest request, ApiException exception) {
        return new ErrorResponse(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        log.error("Unexpected error:", exception);
        return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
    }
}