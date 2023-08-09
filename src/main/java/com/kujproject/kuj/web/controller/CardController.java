package com.kujproject.kuj.web.controller;


import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.dto.card.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/list/{id}/card")
    public ResponseEntity<?> createCard(
            @Valid @RequestBody CreateCardReqDto createCardReqDto,  @PathVariable Long id) {
        createCardReqDto  = cardService.createCard(createCardReqDto, id);
        return ResponseEntity.ok().body(createCardReqDto);
    }

    @GetMapping("/list/{id}/card")
    public ResponseEntity<?> findAllCard(@PathVariable Long id) {
        List<CardRespDto> cardRespDtoList = cardService.findAllCardByListId(id);

        if(cardRespDtoList != null) {
            return ResponseEntity.ok().body(cardRespDtoList);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/card/{id}")
    public ResponseEntity<?> findCard(@PathVariable Long id) {
        CardRespDto cardRespDto = cardService.findCardByCardId(id);

        if(cardRespDto != null) {
            return ResponseEntity.ok().body(cardRespDto);
        }
        return ResponseEntity.notFound().build();
    }


    @PatchMapping("/card/{id}/cover")
    public ResponseEntity<?> updateCardCover(
            @Valid @RequestBody UpdateCardCoverReqDto updateCardCoverReqDto,  @PathVariable Long id) {
        updateCardCoverReqDto = cardService.updateCover(updateCardCoverReqDto, id);
        return ResponseEntity.ok().body(updateCardCoverReqDto);
    }


    @PatchMapping("/card/{id}/description")
    public ResponseEntity<?> updateCardDescription(
            @Valid @RequestBody UpdateCardDescReqDto updateCardDescReqDto,  @PathVariable Long id) {
        updateCardDescReqDto = cardService.updateDescription(updateCardDescReqDto, id);
        return ResponseEntity.ok().body(updateCardDescReqDto);
    }


    @PatchMapping("/card/{id}/label")
    public ResponseEntity<?> updateCardLabel(
            @Valid @RequestBody UpdateCardLabelReqDto updateCardLabelReqDto, @PathVariable Long id) {
        updateCardLabelReqDto = cardService.updateLabel(updateCardLabelReqDto, id);
        return ResponseEntity.ok().body(updateCardLabelReqDto);
    }


    @PatchMapping("/card/{id}/listId")
    public ResponseEntity<?> updateCardListId(
            @Valid @RequestBody UpdateCardListReqDto updateCardListReqDto, @PathVariable Long id) {
        updateCardListReqDto = cardService.updateListId(updateCardListReqDto, id);
        return ResponseEntity.ok().body(updateCardListReqDto);
    }


    @PatchMapping("/card/{id}/order")
    public ResponseEntity<?> updateCardOrder(
            @Valid @RequestBody UpdateCardOrderReqDto updateCardOrderReqDto, @PathVariable Long id) {
        updateCardOrderReqDto = cardService.updateOrder(updateCardOrderReqDto, id);
        return ResponseEntity.ok().body(updateCardOrderReqDto);
    }


    @PatchMapping("/card/{id}/title")
    public ResponseEntity<?> updateCardTitle(
            @Valid @RequestBody UpdateCardTitleReqDto updateCardTitleReqDto, @PathVariable Long id) {
        updateCardTitleReqDto = cardService.updateTitle(updateCardTitleReqDto, id);
        return ResponseEntity.ok().body(updateCardTitleReqDto);
    }


    @PatchMapping("/card/{id}/date")
    public ResponseEntity<?> updateCardDate(
            @Valid @RequestBody UpdateDateReqDto updateDateReqDto, @PathVariable Long id) {

        updateDateReqDto = cardService.updateDate(updateDateReqDto, id);
        return ResponseEntity.ok().body(updateDateReqDto);
    }


    @PatchMapping("/card/{id}/attachment")
    public ResponseEntity<?> updateCardFile(
            @Valid @RequestBody UpdateFileReqDto updateFileReqDto, @PathVariable Long id) {

        updateFileReqDto = cardService.updateFile(updateFileReqDto, id);
        return ResponseEntity.ok().body(updateFileReqDto);
    }



    @DeleteMapping("/card/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id) {

        boolean isDeleted = cardService.deleteCardById(id);
        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
