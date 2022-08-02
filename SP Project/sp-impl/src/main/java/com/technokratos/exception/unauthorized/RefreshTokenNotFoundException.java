package com.technokratos.exception.unauthorized;

public class RefreshTokenNotFoundException extends CustomUnauthorizedException {
    public RefreshTokenNotFoundException() {
        super("Refresh token not found");
    }

}
