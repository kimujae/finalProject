package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.card.*;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
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
    UpdateCardCoverDto updateCover(UpdateCardCoverDto updateCardCoverDto, Long cardId);
    UpdateCardDescDto updateDescription(UpdateCardDescDto updateCardDescDto, Long cardId);
    UpdateCardLabelDto updateLabel(UpdateCardLabelDto updateCardLabelDto, Long cardId);
    UpdateCardOrderDto updateOrder(UpdateCardOrderDto updateCardOrderDto, Long cardId);
    UpdateCardTitleDto updateTitle(UpdateCardTitleDto updateCardTitleDto, Long cardId);
    UpdateCardDateDto updateDate(UpdateCardDateDto updateCardDateDto, Long cardId);
    void updateFile(UpdateCardFilePathDto updateCardFilePathDto, Long cardId) throws IOException;
    UpdateCardListDto updateCardlistId(UpdateCardListDto updateCardListDto, Long cardId);
    void deleteCardById(Long cardId);
    List<CardRespDto> findAllCardByListId(Long listId);
    CardRespDto findCardByCardId(Long cardId) throws IOException;
    List<ChecklistRespDto> findAllChecklistByCardId(Long cardId);
    List<CommentRespDto> findAllCommentByCardId(Long cardId);
    ResponseEntity<?> downloadCardAttachment(Long cardId) throws IOException;
    void deleteCardAttachment(Long cardId) throws IOException;
    void deleteCardDate(Long cardId);
}
