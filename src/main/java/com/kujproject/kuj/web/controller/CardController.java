package com.kujproject.kuj.web.controller;


import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.dto.card.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Card", description = "Card CRUD API입니다.")
public class CardController {

    private final CardService cardService;

    @Operation(summary = "Card 생성", description = "cardllistId를 가진 cardlist에 card를 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/cardlist/{cardlistId}/card")
    public ResponseEntity<?> createCard(
            @Valid @RequestBody CreateCardReqDto createCardReqDto,  @PathVariable Long cardlistId) {

        CardRespDto cardRespDto  = cardService.createCard(createCardReqDto, cardlistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @Operation(summary = "Cards 조회", description = "cardllistId를 가진 cardlist의 cards를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드리스트의 카드 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/cardlist/{cardlistId}/cards")
    public ResponseEntity<ApiResponse> findAllCardsByCardlistId(@PathVariable Long cardlistId) {

        List<CardRespDto> cardRespDtoList = cardService.findAllCardByListId(cardlistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 조회", description = "cardId를 가진 card를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/card/{cardId}")
    public ResponseEntity<?> findCardByCardId(@PathVariable Long cardId) throws IOException {

        CardRespDto cardRespDto = cardService.findCardByCardId(cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 커버를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 커버 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/cover")
    public ResponseEntity<ApiResponse> updateCardCover(
            @Valid @RequestBody UpdateCardCoverDto updateCardCoverDto, @PathVariable Long cardId) {

        updateCardCoverDto = cardService.updateCover(updateCardCoverDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardCoverDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 description을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 description 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/description")
    public ResponseEntity<ApiResponse> updateCardDescription(
            @Valid @RequestBody UpdateCardDescDto updateCardDescDto, @PathVariable Long cardId) {

        updateCardDescDto = cardService.updateDescription(updateCardDescDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardDescDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 label을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 lebel 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/label")
    public ResponseEntity<ApiResponse> updateCardLabel(
            @Valid @RequestBody UpdateCardLabelDto updateCardLabelDto, @PathVariable Long cardId) {

        updateCardLabelDto = cardService.updateLabel(updateCardLabelDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardLabelDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 카드리스트 위치를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드리스트 위치 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/listId")
    public ResponseEntity<ApiResponse> updateCardlistId(
            @Valid @RequestBody UpdateCardListDto updateCardListDto, @PathVariable Long cardId) {

        updateCardListDto = cardService.updateCardlistId(updateCardListDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardListDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 카드리스트 내 순서를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 순서 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/order")
    public ResponseEntity<ApiResponse> updateCardOrder(
            @Valid @RequestBody UpdateCardOrderDto updateCardOrderDto, @PathVariable Long cardId) {

        updateCardOrderDto = cardService.updateOrder(updateCardOrderDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardOrderDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 제목을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 제목 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/title")
    public ResponseEntity<ApiResponse> updateCardTitle(
            @Valid @RequestBody UpdateCardTitleDto updateCardTitleDto, @PathVariable Long cardId) {

        updateCardTitleDto = cardService.updateTitle(updateCardTitleDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardTitleDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 카드리스트 내 날짜를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 날짜 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/card/{cardId}/date")
    public ResponseEntity<ApiResponse> updateCardDate(
            @Valid @RequestBody UpdateCardDateDto updateCardDateDto, @PathVariable Long cardId) {

        updateCardDateDto = cardService.updateDate(updateCardDateDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardDateDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Card 정보 수정", description = "cardId를 가진 card의 첨부파일을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 첨부파일 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping(value = "/card/{cardId}/attachment" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> updateCardAttachment(
            @Valid @ModelAttribute UpdateCardFilePathDto updateCardFilePathDto, @PathVariable Long cardId) throws IOException {

        cardService.updateFile(updateCardFilePathDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result("파일 업로드가 성공적으로 완료되었습니다.")
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Card 삭제", description = "cardId를 가진 card를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "카드 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/card/{cardId}")
    public ResponseEntity<ApiResponse> deleteCard(@PathVariable Long cardId) {

        cardService.deleteCardById(cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Card 날짜 삭제", description = "cardId를 가진 card의 날짜를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "카드 날짜 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/card/{cardId}/dates")
    public ResponseEntity<ApiResponse> deleteAttachment(@PathVariable Long cardId) {

        cardService.deleteCardDate(cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Card 첨부파일 삭제", description = "cardId를 가진 card의 첨부파일을 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "카드 첨부파일 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/card/{cardId}/attachment")
    public ResponseEntity<ApiResponse> deleteDates(@PathVariable Long cardId) throws IOException {

        cardService.deleteCardAttachment(cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Card 첨부파일 다운로드", description = "cardId를 가진 card의 파일을 다운로드(조회)합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드 파일 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/card/{cardId}/attachment")
    public ResponseEntity<?> downloadAttachment(@PathVariable Long cardId) throws IOException {
        return cardService.downloadCardAttachment(cardId);
    }
}
