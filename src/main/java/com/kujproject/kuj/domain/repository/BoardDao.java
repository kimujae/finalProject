package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.board.BoardEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BoardDao extends Repository<BoardEntity, String> {
    BoardEntity save(BoardEntity boardEntity);
    Optional<BoardEntity> findByBoardId(Long boardId);
    Optional<BoardEntity> deleteByBoardId(Long boardId);
}
