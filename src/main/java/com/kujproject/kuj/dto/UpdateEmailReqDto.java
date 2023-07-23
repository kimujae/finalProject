package com.kujproject.kuj.dto;

import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UpdateEmailReqDto {
    @Column(unique = true)
    @Email(message = "유효한 이메일을 입력해주세요.")
    String email;
}
