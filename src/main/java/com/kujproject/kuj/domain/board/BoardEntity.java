package com.kujproject.kuj.domain.board;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long boardId;
    String title;
    String cover;
    boolean isPublic;

//     member , list, starred 와 연관관계 선언
    @OneToMany(mappedBy = "board" , fetch = FetchType.LAZY)
    @JsonManagedReference
    List<Board_UserEntity> users = new ArrayList<>();
}
