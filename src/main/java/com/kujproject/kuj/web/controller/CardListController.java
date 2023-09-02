package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.CardListService;
import com.kujproject.kuj.dto.cardlist.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Cardlist", description = "Cardlist CRUD API입니다.")
public class CardListController {
    private final CardListService cardListService;


    @Operation(summary = "Board에 Cardlist 생성", description = "boardId를 가진 Board에 cardlist를 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드리스트 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/board/{boardId}/cardlist")
    public ResponseEntity<ApiResponse> createCardlist(
            @Valid @RequestBody CreateCardListReqDto createCardListReqDto, @PathVariable Long boardId) {

        CardListRespDto cardListRespDto = cardListService.createCardlist(boardId, createCardListReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardListRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @Operation(summary = "Board의 모든 Cardlists 조회", description = "boardId를 가진 Board에 cardlists를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드의 모든 카드리스트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/board/{boardId}/cardlists")
    public ResponseEntity<ApiResponse> findAllCardlistsByBoardId(@PathVariable Long boardId) {
        long startTime = System.nanoTime();
        CardListRespDtos listRespDtoCardList = cardListService.findAllCardlistByBoardId(boardId);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;
        double milliseconds = (double) executionTime / 1_000_000.0;
        System.out.println("실행 시간: " + milliseconds + " 밀리초");
        return new ResponseEntity<>(ApiResponse.builder()
                .result(listRespDtoCardList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }



    @Operation(summary = "Cardlists 조회", description = "cardlistId를 가진 cardlists를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드리스트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/cardlist/{cardlistId}")
    public ResponseEntity<ApiResponse> findCardlistById(@PathVariable Long cardlistId) {

        CardListRespDto cardListRespDto = cardListService.findCardlistByCardlistID(cardlistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(cardListRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Cardlist 정보 수정", description = "cardlistId를 가진 cardlist의 순서를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드리스트 순서 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/cardlist/{cardlistId}/order")
    public ResponseEntity<ApiResponse> changeCardlistOrder(
            @PathVariable Long cardlistId, @Valid @RequestBody UpdateCardListOrderDto updateCardListOrderDto) {

        updateCardListOrderDto = cardListService.changeCardlistOrder(cardlistId, updateCardListOrderDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardListOrderDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Cardlist 정보 수정", description = "cardlistId를 가진 cardlist의 제목을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드리스트 제목 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/cardlist/{cardlistId}/title")
    public ResponseEntity<ApiResponse> updadteCardlistTitle(
            @PathVariable Long cardlistId, @Valid @RequestBody UpdateCardListTitleDto updateCardListTitleDto) {

        updateCardListTitleDto = cardListService.updateCardlistTitle(cardlistId, updateCardListTitleDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCardListTitleDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Cardlist 삭제", description = "cardlistId를 가진 cardlist를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "카드리스트 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/cardlist/{cardlistId}")
    public ResponseEntity<ApiResponse> deleteByCardlistId(@PathVariable Long cardlistId) {
        cardListService.deleteList(cardlistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
