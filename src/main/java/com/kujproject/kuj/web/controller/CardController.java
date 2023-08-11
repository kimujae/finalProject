package com.kujproject.kuj.web.controller;


import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.dto.card.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/list/{id}/card")
    public ResponseEntity<?> createCard(
            @Valid @RequestBody CreateCardReqDto createCardReqDto,  @PathVariable Long id) {

        CardRespDto cardRespDto  = cardService.createCard(createCardReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/list/{id}/card")
    public ResponseEntity<ApiResponse> findAllCard(@PathVariable Long id) {

        List<CardRespDto> cardRespDtoList = cardService.findAllCardByListId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @GetMapping("/card/{id}")
    public ResponseEntity<?> findCard(@PathVariable Long id) throws IOException {

        CardRespDto cardRespDto = cardService.findCardByCardId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/cover")
    public ResponseEntity<ApiResponse> updateCardCover(
            @Valid @RequestBody UpdateCardCoverDto updateCardCoverDto, @PathVariable Long id) {

        updateCardCoverDto = cardService.updateCover(updateCardCoverDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardCoverDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/description")
    public ResponseEntity<ApiResponse> updateCardDescription(
            @Valid @RequestBody UpdateCardDescDto updateCardDescDto, @PathVariable Long id) {

        updateCardDescDto = cardService.updateDescription(updateCardDescDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardDescDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/label")
    public ResponseEntity<ApiResponse> updateCardLabel(
            @Valid @RequestBody UpdateCardLabelDto updateCardLabelDto, @PathVariable Long id) {

        updateCardLabelDto = cardService.updateLabel(updateCardLabelDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardLabelDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/listId")
    public ResponseEntity<ApiResponse> updateCardListId(
            @Valid @RequestBody UpdateCardListDto updateCardListDto, @PathVariable Long id) {

        updateCardListDto = cardService.updateListId(updateCardListDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardListDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/order")
    public ResponseEntity<ApiResponse> updateCardOrder(
            @Valid @RequestBody UpdateCardOrderDto updateCardOrderDto, @PathVariable Long id) {

        updateCardOrderDto = cardService.updateOrder(updateCardOrderDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardOrderDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/title")
    public ResponseEntity<ApiResponse> updateCardTitle(
            @Valid @RequestBody UpdateCardTitleDto updateCardTitleDto, @PathVariable Long id) {

        updateCardTitleDto = cardService.updateTitle(updateCardTitleDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardTitleDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/card/{id}/date")
    public ResponseEntity<ApiResponse> updateCardDate(
            @Valid @RequestBody UpdateCardDateDto updateCardDateDto, @PathVariable Long id) {

        updateCardDateDto = cardService.updateDate(updateCardDateDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardDateDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/card/{id}/attachment")
    public ResponseEntity<ApiResponse> updateCardFile(
            @Valid @ModelAttribute UpdateCardFilePathDto updateCardFilePathDto, @PathVariable Long id) throws IOException {

        cardService.updateFile(updateCardFilePathDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result("파일 업로드가 성공적으로 완료되었습니다.")
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/card/{id}")
    public ResponseEntity<ApiResponse> deleteCard(@PathVariable Long id) {

        cardService.deleteCardById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/card/{id}/dates")
    public ResponseEntity<ApiResponse> deleteAttachment(@PathVariable Long id) {

        cardService.deleteCardDate(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/card/{id}/attachment")
    public ResponseEntity<ApiResponse> deleteDates(@PathVariable Long id) throws IOException {

        cardService.deleteCardAttachment(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }


    @GetMapping("/card/{id}/attachment")
    public ResponseEntity<?> downloadAttachment(@PathVariable Long id) throws IOException {

        return cardService.downloadCardAttachment(id);
    }
}
