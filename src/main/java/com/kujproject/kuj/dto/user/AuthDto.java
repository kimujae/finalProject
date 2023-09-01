package com.kujproject.kuj.dto.user;

import jakarta.validation.constraints.Null;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AuthDto {
    private String userName;
    private String password;
    @Null
    private String role;
}
