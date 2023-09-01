package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.cardlist.CreateCardListReqDto;
import com.kujproject.kuj.dto.cardlist.CardListRespDto;
import com.kujproject.kuj.dto.cardlist.UpdateCardListOrderDto;
import com.kujproject.kuj.dto.cardlist.UpdateCardListTitleDto;

import java.util.List;

public interface CardListService {
    CardListRespDto findCardByCardlistID(Long cardlistId);
    List<CardListRespDto> findAllCardlistByBoardId(Long boardId);
    UpdateCardListOrderDto changeCardlistOrder(Long cardlistId, UpdateCardListOrderDto updateCardListOrderDto);
    UpdateCardListTitleDto updateCardlistTitle(Long cardlistId, UpdateCardListTitleDto updateCardListTitleDto);
    CardListRespDto createCardlist(Long boardId, CreateCardListReqDto createCardListReqDto);
    void deleteList(Long cardlistId);
}
