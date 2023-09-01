package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import org.springframework.data.repository.Repository;

public interface BoardAndUserDao extends Repository<BoardAndUserEntity, String> {
    BoardAndUserEntity save(BoardAndUserEntity board_And_userEntity);
}
