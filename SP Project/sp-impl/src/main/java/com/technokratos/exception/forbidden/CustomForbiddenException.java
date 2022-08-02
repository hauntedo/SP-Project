package com.technokratos.exception.forbidden;

import com.technokratos.exception.GlobalServiceException;
import org.springframework.http.HttpStatus;

public class CustomForbiddenException extends GlobalServiceException {

    public CustomForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
