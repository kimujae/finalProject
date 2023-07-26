package com.kujproject.kuj.domain.board_user;

import com.kujproject.kuj.domain.board.CustomBoardEntity;
import com.kujproject.kuj.domain.user.CustomUserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board_UserEntity {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    CustomBoardEntity boardEntity; // 사용자 정의 entity 선언(boardId, boardName)

    @ManyToOne
    @JoinColumn(name = "user_id")
    CustomUserEntity userEntity; // 사용자 정의 entity 선언(userId, userName)
}
