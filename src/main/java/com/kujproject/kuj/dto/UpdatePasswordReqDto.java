package com.kujproject.kuj.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class UpdatePasswordReqDto {
    //EqualsField적용 해야함.
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    String password;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    String password2;
}
