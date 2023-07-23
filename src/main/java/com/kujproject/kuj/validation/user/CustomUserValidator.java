package com.kujproject.kuj.validation.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomUserValidator {

    public static class UserIdValidator implements ConstraintValidator<IdDupCheckValidator, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            //


            return false;
        }
    }

    public static class UserEmailValidator implements ConstraintValidator<EmailDupCheckValidator, String> {
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            //


            return false;
        }
    }
}
