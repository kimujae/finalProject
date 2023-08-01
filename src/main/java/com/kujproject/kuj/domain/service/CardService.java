package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.dto.card.*;

import java.util.List;

public interface CardService {
    /*
        0. 카드생성 ok
        1. 카드 컬럼 업데이트 ok
        2. 카드 삭제 ok
        3. 카드 이동(리스트 간) ok
        4. 카드 읽어오기
        5. 리스트의 카드들 모두 가져오기
     */
    CardRespDto createCard(CreateCardReqDto createCardReqDto, Long listId);
    UpdateCardCoverReqDto updateCover(UpdateCardCoverReqDto updateCardCoverReqDto, Long cardId);
    UpdateCardDescReqDto updateDescription(UpdateCardDescReqDto updateCardDescReqDto, Long cardId);
    UpdateCardLabelReqDto updateLabel(UpdateCardLabelReqDto updateCardLabelReqDto, Long cardId);
    UpdateCardOrderReqDto updateOrder(UpdateCardOrderReqDto updateCardOrderReqDto, Long cardId);
    UpdateCardTitleReqDto updateTitle(UpdateCardTitleReqDto updateCardTitleReqDto, Long cardId);
    UpdateDateReqDto updateDate(UpdateDateReqDto updateDateReqDto, Long cardId);
    UpdateFileReqDto updateFile(UpdateFileReqDto updateFileReqDto, Long cardId);
    UpdateCardListReqDto updateListId(UpdateCardListReqDto updateCardListReqDto, Long cardId);
    boolean deleteCardById(Long cardId);
    List<CardRespDto> findAllCardByListId(Long listId);
    CardRespDto findCardByCardId(Long cardId);
}
