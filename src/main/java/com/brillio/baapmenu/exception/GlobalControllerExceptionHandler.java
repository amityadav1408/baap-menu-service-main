package com.brillio.baapmenu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ErrorModel handleInternalServerError(Exception exception) {
        return ErrorModel.builder().errorMessage(extractErrorMessage(exception)).build();
    }

    private String extractErrorMessage(Throwable throwable) {
        return throwable.getCause() != null ? throwable.getCause().getMessage() : throwable.getMessage();
    }
}
