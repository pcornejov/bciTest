package com.pcornejo.bciJavaTest.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ValidIntegerValidator implements ConstraintValidator<ValidInteger, String> {

    @Override
    public boolean isValid(String stringInteger, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.isBlank(stringInteger)) {
            return false;
        }
        try {
            Integer i = Integer.parseInt(stringInteger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
