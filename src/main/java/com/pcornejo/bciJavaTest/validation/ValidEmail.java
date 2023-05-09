package com.pcornejo.bciJavaTest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidEmailValidator.class)
public @interface ValidEmail {

    String message() default "invalid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
