package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CardListDao extends Repository<CardListEntity, Long> {
    //@Query("SELECT c FROM CardListEntity c WHERE c.board = :board")
    List<CardListEntity> findAllByBoard(BoardEntity board);
    Optional<CardListEntity> findByCardlistId(Long cardlistId);
    int deleteByCardlistId(Long cardlistId);
    CardListEntity save(CardListEntity cardlist);
}
