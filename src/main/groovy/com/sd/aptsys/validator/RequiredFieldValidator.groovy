package com.sd.aptsys.validator

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class RequiredFieldValidator implements ConstraintValidator<RequiredField, Object> {

    @Override
    void initialize(RequiredField constraintAnnotation) {
    }

    @Override
    boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof String) {
            return value != null && value.trim().length() > 0
        } else if (value instanceof Date) {
            return value != null
        }
        return false
    }

}
