package com.kujproject.kuj.domain.board;

import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    Long boardId;
    String title;
    String isPublic;

    // member , list, starred 와 연관관계 선언
    @OneToMany(mappedBy = "board")
    List<Board_UserEntity> boards = new ArrayList<>();
}
