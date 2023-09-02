package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.cardlist.*;

public interface CardListService {
    CardListRespDto findCardlistByCardlistID(Long cardlistId);
    CardListRespDtos findAllCardlistByBoardId(Long boardId);
    UpdateCardListOrderDto changeCardlistOrder(Long cardlistId, UpdateCardListOrderDto updateCardListOrderDto);
    UpdateCardListTitleDto updateCardlistTitle(Long cardlistId, UpdateCardListTitleDto updateCardListTitleDto);
    CardListRespDto createCardlist(Long boardId, CreateCardListReqDto createCardListReqDto);
    void deleteList(Long cardlistId);
}
