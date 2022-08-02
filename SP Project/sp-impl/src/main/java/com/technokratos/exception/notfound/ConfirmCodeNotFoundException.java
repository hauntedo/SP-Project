package com.technokratos.exception.notfound;

public class ConfirmCodeNotFoundException extends DataNotFoundException {
    public ConfirmCodeNotFoundException() {
        super("confirm code not found");
    }
}
