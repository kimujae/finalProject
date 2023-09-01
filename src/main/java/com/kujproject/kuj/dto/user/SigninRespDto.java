package com.kujproject.kuj.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SigninRespDto {
    private String userId;
    private String token;
}
