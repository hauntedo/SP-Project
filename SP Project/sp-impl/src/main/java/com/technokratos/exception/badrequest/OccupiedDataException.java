package com.technokratos.exception.badrequest;

public class OccupiedDataException extends BadRequestException {

    public OccupiedDataException(String message) {
        super(message);
    }
}
