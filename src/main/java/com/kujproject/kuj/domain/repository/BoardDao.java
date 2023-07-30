package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.board.BoardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardDao extends Repository<BoardEntity, String> {
    BoardEntity save(BoardEntity boardEntity);
    //Optional<List<Board_UserEntity>> findByBoardId(Long boardId);
    Optional<BoardEntity> deleteByBoardId(Long boardId);

    @Query("SELECT DISTINCT b FROM board b LEFT JOIN FETCH b.users u WHERE b.boardId = :boardId")
    Optional<BoardEntity> findByBoardId(@Param("boardId") Long boardId);

}
