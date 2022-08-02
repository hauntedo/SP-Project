package com.technokratos.exception.notfound;

public class FileNotFoundException extends DataNotFoundException {

    public FileNotFoundException() {
        super("File not found");
    }
}
