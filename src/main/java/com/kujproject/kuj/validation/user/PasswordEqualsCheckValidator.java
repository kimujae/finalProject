package com.kujproject.kuj.validation.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;

public class PasswordEqualsCheckValidator implements ConstraintValidator<EqualsFieldValidator, Object> {
    private String password;
    private String checkPassword;

    @Override
    public void initialize(EqualsFieldValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.password = constraintAnnotation.field1();
        this.checkPassword = constraintAnnotation.field2();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        try {
            Object fieldValue1 = BeanUtils.getPropertyDescriptor(value.getClass(), password).getReadMethod().invoke(value);
            Object fieldValue2 = BeanUtils.getPropertyDescriptor(value.getClass(), checkPassword).getReadMethod().invoke(value);

            return fieldValue2.equals(fieldValue1);
        } catch (Exception e) {
            return false;
        }
    }
}
