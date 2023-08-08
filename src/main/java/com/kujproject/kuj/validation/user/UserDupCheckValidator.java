package com.kujproject.kuj.validation.user;

import com.kujproject.kuj.domain.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserDupCheckValidator {
    public static class IdDupCheck implements ConstraintValidator<IdDupCheckValidator, String> {
        private final UserService userService;

        public IdDupCheck(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void initialize(IdDupCheckValidator constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(userService.existByUserId(value)) {
                return false;
            }
            return true;
        }
    }


    public static class EmailDupCheck implements ConstraintValidator<EmailDupCheckValidator, String> {
        private final UserService userService;

        public EmailDupCheck(UserService userService) {
            this.userService = userService;
        }

        @Override
        public void initialize(EmailDupCheckValidator constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(userService.existByEmail(value)) {
                return false;
            }
            return true;
        }
    }


    public static class PhoneNumDupCheck implements ConstraintValidator<PhoneNumDupCheckValidator, String> {
        private final UserService userService;

        public PhoneNumDupCheck(UserService userService) {
            this.userService = userService;
        }


        @Override
        public void initialize(PhoneNumDupCheckValidator constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(userService.existByPhoneNum(value)) {
                return false;
            }
            return true;
        }
    }
}
