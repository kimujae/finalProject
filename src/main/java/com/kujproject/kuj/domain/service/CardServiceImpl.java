package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.comment.CommentEntity;
import com.kujproject.kuj.domain.repository.CardDao;
import com.kujproject.kuj.domain.repository.CardListDao;
import com.kujproject.kuj.dto.card.*;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.common.utils.FileManager;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService{

    private final CardDao cardDao;
    private final CardListDao cardListDao;
    private final FileManager fileManager;



    @Override
    public CardRespDto createCard(CreateCardReqDto createCardReqDto, Long cardlistId) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));


        CardEntity card = CardEntity.convertedBy(createCardReqDto, cardlist);
        cardDao.save(card);
        return CardRespDto.convertedBy(card);
    }

    @Override
    @Transactional
    public UpdateCardCoverDto updateCover(UpdateCardCoverDto updateCardCoverDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        card.changeCover(updateCardCoverDto);
        cardDao.save(card);
        return updateCardCoverDto;
    }


    @Override
    @Transactional
    public UpdateCardDescDto updateDescription(UpdateCardDescDto updateCardDescDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        card.changeDescription(updateCardDescDto);
        cardDao.save(card);
        return updateCardDescDto;
    }

    @Override
    @Transactional
    public UpdateCardLabelDto updateLabel(UpdateCardLabelDto updateCardLabelDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        card.changeLabel(updateCardLabelDto);
        cardDao.save(card);
        return updateCardLabelDto;
    }

    @Override
    @Transactional
    public UpdateCardOrderDto updateOrder(UpdateCardOrderDto updateCardOrderDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        card.changeCardOrder(updateCardOrderDto);
        cardDao.save(card);
        return updateCardOrderDto;
    }

    @Override
    @Transactional
    public UpdateCardTitleDto updateTitle(UpdateCardTitleDto updateCardTitleDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        card.changeTitle(updateCardTitleDto);
        cardDao.save(card);
        return updateCardTitleDto;
    }

    @Override
    @Transactional
    public UpdateCardDateDto updateDate(UpdateCardDateDto updateCardDateDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        card.changeDate(updateCardDateDto);
        cardDao.save(card);
        return updateCardDateDto;
    }

    @Override
    @Transactional
    public void updateFile(UpdateCardFilePathDto updateCardFilePathDto, Long cardId) throws IOException {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        String[] filePath = fileManager.saveFile(updateCardFilePathDto.getAttachment(), card.getStoredFileName());

        card.changeUploadFileName(filePath[0]);
        card.changeStoredFileName(filePath[1]);
        cardDao.save(card);
    }

    @Override
    @Transactional
    public UpdateCardListDto updateCardlistId(UpdateCardListDto updateCardListDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(updateCardListDto.getListId());

        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));

        card.changeList(cardlist);
        cardDao.save(card);
        return updateCardListDto;
    }

    @Override
    @Transactional
    public void deleteCardById(Long cardId) {
        int deletedCount = cardDao.deleteByCardId(cardId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND);
        }
    }

    @Override
    public List<CardRespDto> findAllCardByListId(Long cardlistId) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));


        List<CardEntity> cards = cardDao.findAllByCardlist(cardlist);
        List<CardRespDto> cardRespDtoList = new ArrayList<>();
        for (CardEntity card : cards) {
            cardRespDtoList.add(CardRespDto.convertedBy(card));
        }
        return cardRespDtoList;
    }


    @Override
    public CardRespDto findCardByCardId(Long cardId) throws IOException {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        String downloadFilePath = "card/" + card.getCardId() + "/attachment";
        return CardRespDto.convertedBy(card, downloadFilePath);
    }

    @Override
    public List<ChecklistRespDto> findAllChecklistByCardId(Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        List<ChecklistEntity>checklistEntityList = card.getChecklist();
        List<ChecklistRespDto> checklistRespDtoList = new ArrayList<>();
        for(ChecklistEntity checklist : checklistEntityList) {
            checklistRespDtoList.add(ChecklistRespDto.convertedBy(checklist));
        }

        return checklistRespDtoList;
    }


    @Override
    public List<CommentRespDto> findAllCommentByCardId(Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        List<CommentEntity> commentEntityList = card.getComment();
        List<CommentRespDto> commentRespDtoList = new ArrayList<>();
        String userId =""; // 세션정보 가져오기

        for(CommentEntity commentEntity : commentEntityList) {
            commentRespDtoList.add(CommentRespDto.convertedBy(commentEntity, userId));
        }
        return commentRespDtoList;
    }


    public ResponseEntity<?> downloadCardAttachment(Long cardId) throws IOException {
            Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
            CardEntity card = cardEntity.orElseThrow(() ->
                    new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

            String uploadFileName = card.getUploadFileName();
            String storedFileName = card.getStoredFileName();
            String filePath = fileManager.loadFilePath(storedFileName).toString();

            UrlResource resource = new UrlResource("file:" + filePath);
            String encodedUploadFileName = UriUtils.encode(uploadFileName,
                    StandardCharsets.UTF_8);

            String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(resource);
    }

    @Override
    @Transactional
    public void deleteCardAttachment(Long cardId) throws IOException {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        // 파일 삭제 로직
        Path filePath = fileManager.loadFilePath(card.getStoredFileName());
        if(Files.exists(filePath)) {
            Files.delete(filePath);
        }


        card.changeStoredFileName(null);
        card.changeUploadFileName(null);
        cardDao.save(card);
    }

    @Override
    @Transactional
    public void deleteCardDate(Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        UpdateCardDateDto updateCardDateDto = new UpdateCardDateDto();
        card.changeDate(updateCardDateDto);
        cardDao.save(card);
    }


}
