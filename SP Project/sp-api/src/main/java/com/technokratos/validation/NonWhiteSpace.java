package com.technokratos.validation;

import com.technokratos.validation.impl.WhiteSpaceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = WhiteSpaceValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NonWhiteSpace {

    String message() default "Must not contain white space";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default  {};



}
