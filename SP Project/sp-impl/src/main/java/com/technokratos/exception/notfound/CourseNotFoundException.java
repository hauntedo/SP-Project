package com.technokratos.exception.notfound;

public class CourseNotFoundException extends DataNotFoundException {
    public CourseNotFoundException() {
        super("Course not found");
    }

    public CourseNotFoundException(String message) {
        super(message);
    }
}
