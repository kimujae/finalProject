package com.kujproject.kuj.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdatePasswordReqDto {
    //EqualsField적용 해야함.
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    String password;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    String password2;
}
