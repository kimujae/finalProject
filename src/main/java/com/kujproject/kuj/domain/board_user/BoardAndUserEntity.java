package com.kujproject.kuj.domain.board_user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name ="board_user")
public class BoardAndUserEntity {
    @Id
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @JsonBackReference
    BoardEntity board; // 사용자 정의 entity 선언(boardId, boardName)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    UserEntity user; // 사용자 정의 entity 선언(userId, userName)
}
