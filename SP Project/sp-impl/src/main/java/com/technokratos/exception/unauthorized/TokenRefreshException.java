package com.technokratos.exception.unauthorized;

import com.technokratos.exception.GlobalServiceException;
import org.springframework.http.HttpStatus;

public class TokenRefreshException extends GlobalServiceException {
    public TokenRefreshException(String token, String message) {
        super(HttpStatus.UNAUTHORIZED, String.format("Failed for [%s]: %s", token, message));
    }
}
