package com.technokratos.exception.badrequest;

public class FailureLoginException extends BadRequestException {
    public FailureLoginException() {
        super("Incorrect data");
    }
}
