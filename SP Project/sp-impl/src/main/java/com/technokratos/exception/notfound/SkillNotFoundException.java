package com.technokratos.exception.notfound;

public class SkillNotFoundException extends DataNotFoundException {


    public SkillNotFoundException(String message) {
        super(message);
    }

    public SkillNotFoundException() {
        super("Skill not found");
    }
}
