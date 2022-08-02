package com.technokratos.exception.notfound;

public class AcademyGroupNotFoundException extends DataNotFoundException {
    public AcademyGroupNotFoundException(String message) {
        super(message);
    }

    public AcademyGroupNotFoundException() {
        super("Academy group not found");
    }
}
