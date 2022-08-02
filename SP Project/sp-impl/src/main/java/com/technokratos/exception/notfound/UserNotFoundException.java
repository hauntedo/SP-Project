package com.technokratos.exception.notfound;

public class UserNotFoundException extends DataNotFoundException {
    public UserNotFoundException() {
        super("User not found");
    }
}
