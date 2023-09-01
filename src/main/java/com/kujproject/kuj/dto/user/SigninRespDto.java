package com.kujproject.kuj.dto.user;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@Hidden
public class SigninRespDto {
    private String userId;
    private String token;
}
