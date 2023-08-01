package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.domain.repository.CardDao;
import com.kujproject.kuj.domain.repository.ListDao;
import com.kujproject.kuj.dto.card.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{

    private final CardDao cardDao;
    private final ListDao listDao;

    public CardServiceImpl(CardDao cardDao, ListDao listDao) {
        this.cardDao = cardDao;
        this.listDao = listDao;
    }


    @Override
    public CreateCardReqDto createCard(CreateCardReqDto createCardReqDto, Long listId) {
        CardEntity card = new CardEntity();
        Optional<ListEntity> listEntity = listDao.findByListId(listId);

        if(listEntity.isPresent()) {
            ListEntity list = listEntity.get();
            card.setList(list);
            card.setTitle(createCardReqDto.getTitle());

            cardDao.save(card);

            return createCardReqDto;
        }
        // 리스트의 카드 사이즈를 가져와서 순서를 지정해야한다.
        //card.setCardOrder();
        return null;
    }

    @Override
    public UpdateCardCoverReqDto updateCover(UpdateCardCoverReqDto updateCardCoverReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setCover(updateCardCoverReqDto.getCover());

            cardDao.save(card);
            return updateCardCoverReqDto;
        }
        return null;
    }

    @Override
    public UpdateCardDescReqDto updateDescription(UpdateCardDescReqDto updateCardDescReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setDescription(updateCardDescReqDto.getDescription());

            cardDao.save(card);
            return updateCardDescReqDto;
        }
        return null;
    }

    @Override
    public UpdateCardLabelReqDto updateLabel(UpdateCardLabelReqDto updateCardLabelReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setLabel(updateCardLabelReqDto.getLabel());

            cardDao.save(card);
            return updateCardLabelReqDto;
        }
        return null;
    }

    @Override
    public UpdateCardOrderReqDto updateOrder(UpdateCardOrderReqDto updateCardOrderReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setCardOrder(updateCardOrderReqDto.getCardOrder());

            cardDao.save(card);
            return updateCardOrderReqDto;
        }
        return null;
    }

    @Override
    public UpdateCardTitleReqDto updateTitle(UpdateCardTitleReqDto updateCardTitleReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setTitle(updateCardTitleReqDto.getTitle());

            cardDao.save(card);
            return updateCardTitleReqDto;
        }
        return null;    }

    @Override
    public UpdateDateReqDto updateDate(UpdateDateReqDto updateDateReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setStartdate(updateDateReqDto.getStartdate());
            card.setDuedate(updateDateReqDto.getDuedate());

            cardDao.save(card);
            return updateDateReqDto;
        }
        return null;
    }

    @Override
    public UpdateFileReqDto updateFile(UpdateFileReqDto updateFileReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            card.setAttachmentPath(updateFileReqDto.getAttachmentPath());

            cardDao.save(card);
            return updateFileReqDto;
        }
        return null;
    }

    @Override
    public UpdateCardListReqDto updateListId(UpdateCardListReqDto updateCardListReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);
        Optional<ListEntity> listEntity = listDao.findByListId(updateCardListReqDto.getListId());
        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            if(listEntity.isPresent()){
                ListEntity list = listEntity.get();
                card.setList(list);

                cardDao.save(card);
                return updateCardListReqDto;
            }
        }
        return null;
    }

    @Override
    public boolean deleteCardById(Long cardId) {
        // Optional 안쓰면 무엇을 반환하는지 찾아보자
        cardDao.deleteByCardId(cardId);
        return true;
    }

    @Override
    public List<CardRespDto> findAllCardByListId(Long listId) {
        Optional<ListEntity> listEntity= listDao.findByListId(listId);

        if(listEntity.isPresent()) {
            ListEntity list = listEntity.get();
            Optional<List<CardEntity>> cards = cardDao.findCardEntitiesByList(list);

            if(cards.isPresent()) {
                List<CardEntity> cardList = cards.get();
                List<CardRespDto> cardRespDtoList = new ArrayList<>();
                for (CardEntity card : cardList) {
                    CardRespDto cardRespDto = new CardRespDto();

                    cardRespDto.setCardOrder(card.getCardOrder());
                    cardRespDto.setDescription(card.getDescription());
                    cardRespDto.setStartdate(card.getStartdate());
                    cardRespDto.setDuedate(card.getDuedate());
                    cardRespDto.setCover(card.getCover());
                    cardRespDto.setLabel(card.getLabel());
                    cardRespDto.setAttachmentPath(card.getAttachmentPath());
                    cardRespDto.setTitle(card.getTitle());

                    cardRespDtoList.add(cardRespDto);
                }
                return cardRespDtoList;
            }
        }
        return null;
    }

    @Override
    public CardRespDto findCardByCardId(Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();
            CardRespDto cardRespDto = new CardRespDto();

            cardRespDto.setCardOrder(card.getCardOrder());
            cardRespDto.setDescription(card.getDescription());
            cardRespDto.setStartdate(card.getStartdate());
            cardRespDto.setDuedate(card.getDuedate());
            cardRespDto.setCover(card.getCover());
            cardRespDto.setLabel(card.getLabel());
            cardRespDto.setAttachmentPath(card.getAttachmentPath());
            cardRespDto.setTitle(card.getTitle());

            return cardRespDto;
        }
        return null;
    }
}
