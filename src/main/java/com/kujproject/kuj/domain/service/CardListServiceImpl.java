package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.repository.CardListDao;
import com.kujproject.kuj.dto.cardlist.CreateCardListReqDto;
import com.kujproject.kuj.dto.cardlist.CardListRespDto;
import com.kujproject.kuj.dto.cardlist.UpdateCardListOrderDto;
import com.kujproject.kuj.dto.cardlist.UpdateCardListTitleDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardListServiceImpl implements CardListService {
    private final CardListDao cardListDao;
    private final BoardDao boardDao;


    @Override
    public CardListRespDto findCardByCardlistID(Long cardlistId) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));

        return CardListRespDto.convertedBy(cardlist);
    }


    @Override
    public List<CardListRespDto> findAllCardlistByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));


        List<CardListEntity> cardlistEntityList = cardListDao.findAllByBoard(board);
        List<CardListRespDto> cardlistRespDtos = new ArrayList<>();
        for(CardListEntity cardlists : cardlistEntityList) {
            cardlistRespDtos.add(CardListRespDto.convertedBy(cardlists));
        }
        return cardlistRespDtos;
    }


    /*
    순서변경은 배치요청?
     */
    @Override
    @Transactional
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
    public UpdateCardListTitleDto updateCardlistTitle(Long cardlistId, UpdateCardListTitleDto updateCardListTitleDto) {
        Optional<CardListEntity> cardlistEntity = cardListDao.findByCardlistId(cardlistId);
        CardListEntity cardlist = cardlistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARDLIST_NOT_FOUND));

        cardlist.changeTitle(updateCardListTitleDto);
        cardListDao.save(cardlist);
        return updateCardListTitleDto;
    }


    @Override
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
    public void deleteList(Long cardlistId) {
        int deletedCount = cardListDao.deleteByCardlistId(cardlistId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
        }
    }
}
