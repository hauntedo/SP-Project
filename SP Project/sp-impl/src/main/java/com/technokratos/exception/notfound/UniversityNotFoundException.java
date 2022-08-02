package com.technokratos.exception.notfound;

public class UniversityNotFoundException extends DataNotFoundException {
    public UniversityNotFoundException(String message) {
        super(message);
    }

    public UniversityNotFoundException() {
        super("University not found");
    }
}
