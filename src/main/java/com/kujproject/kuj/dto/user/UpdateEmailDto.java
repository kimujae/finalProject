package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import com.kujproject.kuj.validation.user.EmailDupCheckValidator;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateEmailDto {
    @Column(unique = true)
    @Email(message = UserConstraint.EMAIL)
    @Size(max = 100, message = UserConstraint.EMAIL_SIZEMAX_MSG)
    @EmailDupCheckValidator
    private String email;
}
