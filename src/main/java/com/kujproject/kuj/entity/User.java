package com.kujproject.kuj.entity;

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
public class User{
    @Id
    String userId;
    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    String passWord;
    @NotEmpty(message = "이름은 필수 항목입니다.")
    String name;

    @Column(unique = true)
    @Email(message = "유효한 이메일을 입력해주세요.")
    String email;
    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    String phone_num;

    String role;

    public User(String userId, String passWord, String name, String email, String phone_num, String role){
        this.userId = userId;
        this.passWord = passWord;
        this.name = name;
        this.email = email;
        this.phone_num = phone_num;
        this.role = role;
    }
}