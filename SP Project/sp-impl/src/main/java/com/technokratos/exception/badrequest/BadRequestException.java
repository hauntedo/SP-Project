package com.technokratos.exception.badrequest;

import com.technokratos.exception.GlobalServiceException;
import org.springframework.http.HttpStatus;

public class BadRequestException extends GlobalServiceException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
