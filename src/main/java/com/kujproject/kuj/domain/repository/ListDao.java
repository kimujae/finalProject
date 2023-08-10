package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ListDao extends Repository<ListEntity, String> {
    List<ListEntity> findAllByBoard(BoardEntity board);
    Optional<ListEntity> findByListId(Long listId);
    int deleteByListId(Long listId);
    ListEntity save(ListEntity list);
}
