package com.brillio.baapmenu.exception;

import java.io.Serial;

public class ServiceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -9082716801852042830L;

    public ServiceException(String message) {
        super(message);
    }
}
