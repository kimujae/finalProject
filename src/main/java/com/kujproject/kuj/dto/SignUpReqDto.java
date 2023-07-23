package com.kujproject.kuj.dto;

import jakarta.persistence.Column;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class SignUpReqDto {
    @Column(unique = true)
    @NotEmpty(message = "ID는 필수 항목입니다.")
    String userId;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    String password;

    @NotEmpty(message = "비밀번호확인은 필수 항목입니다.")
    String password2;

    @NotEmpty(message = "이름은 필수 항목입니다.")
    String userName;

    @Column(unique = true)
    @Email(message = "유효한 이메일을 입력해주세요.")
    String email;

    @Column(unique = true)
    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    String phoneNum;

    String role;
}
