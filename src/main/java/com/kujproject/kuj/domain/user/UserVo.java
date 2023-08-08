package com.kujproject.kuj.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserVo {
    private String userId;
    private String password;
    private String userName;
    private String email;
    private String phoneNum;
    private String role;
}
