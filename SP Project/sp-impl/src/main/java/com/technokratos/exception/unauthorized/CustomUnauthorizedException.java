package com.technokratos.exception.unauthorized;

import com.technokratos.exception.GlobalServiceException;
import org.springframework.http.HttpStatus;

public class CustomUnauthorizedException extends GlobalServiceException {

    public CustomUnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
