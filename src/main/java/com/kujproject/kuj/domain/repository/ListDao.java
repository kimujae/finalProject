package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.list.ListEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface ListDao extends Repository<ListEntity, String> {
    Optional<List<ListEntity>> findAllByBoardId(Long boardId);
    Optional<ListEntity> findByListId(Long listId);
    Optional<ListEntity> deleteByListId(Long listId);
    ListEntity save(ListEntity list);
}
