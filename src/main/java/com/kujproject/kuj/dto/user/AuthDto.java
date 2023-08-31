package com.kujproject.kuj.dto.user;


import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AuthDto {
    private String userId;
    private String userPassword;
    private String userName;
}
