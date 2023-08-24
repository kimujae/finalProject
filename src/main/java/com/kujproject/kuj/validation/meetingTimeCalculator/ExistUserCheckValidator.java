package com.kujproject.kuj.validation.meetingTimeCalculator;


import com.kujproject.kuj.validation.user.EqualsFieldValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserCheckValidator.class)
public @interface ExistUserCheckValidator {
    String message() default "초대 할 수 없는 사용자입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String board();

    String users();

    @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        ExistUserCheckValidator[] value();
    }
}