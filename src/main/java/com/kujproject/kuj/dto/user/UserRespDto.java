package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.domain.user.UserEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserRespDto {
    private String userId;
    private String userName;
    private String email;
    private String phoneNum;


    public static UserRespDtoBuilder convertedBy(UserEntity userEntity) {
        return UserRespDto.builder()
                .userId(userEntity.getUserId())
                .userName(userEntity.getUserName())
                .email(userEntity.getEmail())
                .phoneNum(userEntity.getPhoneNum());
    }
}
