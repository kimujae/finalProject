package com.kujproject.kuj.dto.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class UpdateProfileReqDto {
    @NotEmpty(message = "이름은 필수 항목입니다.")
    String userName;
}
