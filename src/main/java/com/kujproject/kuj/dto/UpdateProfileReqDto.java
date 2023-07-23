package com.kujproject.kuj.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UpdateProfileReqDto {
    @NotEmpty(message = "이름은 필수 항목입니다.")
    String userName;
}
