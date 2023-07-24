package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.validation.user.EmailDupCheckValidator;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
public class UpdateEmailReqDto {
    @Column(unique = true)
    @Email(message = "유효한 이메일을 입력해주세요.")
    @EmailDupCheckValidator
    String email;
}
