package com.kujproject.kuj.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User{
    @Id
    String userId;
    String passWord;
    String name;
    String email;
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
