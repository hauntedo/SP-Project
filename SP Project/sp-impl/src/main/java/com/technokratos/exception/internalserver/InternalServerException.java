package com.technokratos.exception.internalserver;

import com.technokratos.exception.GlobalServiceException;
import org.springframework.http.HttpStatus;

public class InternalServerException extends GlobalServiceException {

    public InternalServerException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
