package com.kujproject.kuj.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    String userId;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    String password;
    @NotEmpty(message = "이름은 필수 항목입니다.")
    String userName;

    @Column(unique = true)
    @Email(message = "유효한 이메일을 입력해주세요.")
    String email;
    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    String phone_num;

    String role;

    public UserEntity(String userId, String passWord, String userName, String email, String phone_num, String role){
        this.userId = userId;
        this.password = passWord;
        this.userName = userName;
        this.email = email;
        this.phone_num = phone_num;
        this.role = role;
    }
}
