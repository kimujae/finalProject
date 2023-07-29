package com.kujproject.kuj.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserEntity {
    @Id
    String userId;
    String password;
    String userName;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String phoneNum;
    String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Board_UserEntity> boards = new ArrayList<>();
}
