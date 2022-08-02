package com.technokratos.validation.impl;

import com.technokratos.validation.NonWhiteSpace;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WhiteSpaceValidator implements ConstraintValidator<NonWhiteSpace, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !StringUtils.containsWhitespace(value);
    }
}


