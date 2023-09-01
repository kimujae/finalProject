package com.kujproject.kuj.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import com.kujproject.kuj.dto.user.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class UserEntity {
    @Id
    private String userId;
    private String password;
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNum;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<BoardAndUserEntity> boards = new ArrayList<>();

    public void changePassword(UpdatePasswordDto updatePasswordDto) {
        this.password = updatePasswordDto.getPassword();
    }

    public void changeProfile(UpdateProfileDto updateProfileDto) {
        this.userName = updateProfileDto.getUserName();
    }

    public void changeEmail(UpdateEmailDto updateEmailDto) {
        this.userId = updateEmailDto.getEmail();
    }

    public void changePhoneNum(UpdatePhoneNumDto updatePhoneNumDto) {
        this.phoneNum = updatePhoneNumDto.getPhoneNum();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static UserEntity convertedBy(SignUpReqDto signUpReqDto) {
        return UserEntity.builder()
                .userId(signUpReqDto.getUserId())
                .password(signUpReqDto.getPassword())
                .userName(signUpReqDto.getUserName())
                .email(signUpReqDto.getEmail())
                .phoneNum(signUpReqDto.getPhoneNum())
                .build();
    }
}
