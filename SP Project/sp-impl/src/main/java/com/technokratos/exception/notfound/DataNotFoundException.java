package com.technokratos.exception.notfound;

import com.technokratos.exception.GlobalServiceException;
import org.springframework.http.HttpStatus;

public class DataNotFoundException extends GlobalServiceException {

    public DataNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
