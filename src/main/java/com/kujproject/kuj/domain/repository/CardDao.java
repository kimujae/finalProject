package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CardDao extends Repository<CardEntity, String> {
    CardEntity save(CardEntity card);
    List<CardEntity> findAllByList(ListEntity list);
    Optional<CardEntity> findByCardId(Long cardId);
    int deleteByCardId(Long cardId);
}
