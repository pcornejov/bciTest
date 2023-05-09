package com.pcornejo.bciJavaTest.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValidIntegerValidator.class)
public @interface ValidInteger {

    String message() default "El número debe ser entero.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
