package com.kujproject.kuj.validation.user;

import com.kujproject.kuj.domain.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomUserValidator {

    public static class UserIdValidator implements ConstraintValidator<IdDupCheckValidator, String> {

        private final UserService userService;

        public UserIdValidator(UserService userService) {
            this.userService = userService;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(userService.existByUserId(value)) {
                return false;
            }
            return true;
        }
    }

    public static class UserEmailValidator implements ConstraintValidator<EmailDupCheckValidator, String> {
        private final UserService userService;

        public UserEmailValidator(UserService userService) {
            this.userService = userService;
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if(userService.existByEmail(value)) {
                return false;
            }
            return true;
        }
    }
}
