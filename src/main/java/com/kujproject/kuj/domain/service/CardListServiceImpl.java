package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.repository.CardListDao;
import com.kujproject.kuj.dto.cardlist.*;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardListServiceImpl implements CardListService {
    private final CardListDao cardListDao;
    private final BoardDao boardDao;


    @Override
    public CardListRespDto findCardlistByCardlistID(Long cardlistId) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));

        return CardListRespDto.convertedBy(cardlist);
    }


    @Override
    //@Transactional(readOnly = true)
    //@Cacheable(key = "#boardId", value = "cache1")
    public CardListRespDtos findAllCardlistByBoardId(Long boardId) {
        long startTime = System.nanoTime();
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));


        List<CardListEntity> cardlistEntityList = cardListDao.findAllByBoard(board);
        List<CardListRespDto> cardlistRespDtos = new ArrayList<>();

        for(CardListEntity cardlists : cardlistEntityList) {
            cardlistRespDtos.add(CardListRespDto.convertedBy(cardlists));
        }
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        double milliseconds = (double) executionTime / 1_000_000.0;
        System.out.println("실행 시간: " + milliseconds + " 밀리초");
        CardListRespDtos cardlist = new CardListRespDtos(cardlistRespDtos);

        return cardlist;
    }


    /*
    순서변경은 배치요청?
     */
    @Override
    @Transactional
    @CacheEvict
    public UpdateCardListOrderDto changeCardlistOrder(Long cardlistId, UpdateCardListOrderDto updateCardListOrderDto) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));

        cardlist.changeListOrder(updateCardListOrderDto);
        cardListDao.save(cardlist);
        return updateCardListOrderDto;
    }


    @Override
    @Transactional
    @CacheEvict
    public UpdateCardListTitleDto updateCardlistTitle(Long cardlistId, UpdateCardListTitleDto updateCardListTitleDto) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));

        cardlist.changeTitle(updateCardListTitleDto);
        cardListDao.save(cardlist);
        return updateCardListTitleDto;
    }


    @Override
    @CachePut
    public CardListRespDto createCardlist(Long boardId, CreateCardListReqDto createCardListReqDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        CardListEntity cardlistEntity = CardListEntity.convertedBy(createCardListReqDto, board);
        cardListDao.save(cardlistEntity);

        CardListRespDto cardListRespDto = CardListRespDto.convertedBy(cardlistEntity);
        return cardListRespDto;
    }


    @Override
    @Transactional
    @CacheEvict
    public void deleteList(Long cardlistId) {
        int deletedCount = cardListDao.deleteByCardlistId(cardlistId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
        }
    }
}
