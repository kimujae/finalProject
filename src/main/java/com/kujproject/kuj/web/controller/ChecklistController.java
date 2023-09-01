package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistProgressDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistTitleDto;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Checklist", description = "Checklist CRUD API입니다.")
public class ChecklistController {
    private final ChecklistService checklistService;
    private final CardService cardService;

    @Operation(summary = "Card에 Checklist 생성", description = "cardId를 가진 card에 checklist를 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "체크리스트 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/card/{cardId}/checklist")
    public ResponseEntity<ApiResponse> createChecklist(
            @PathVariable Long cardId, @Valid @RequestBody CreateChecklistReqDto createChecklistReqDto) {

        ChecklistRespDto checklistRespDto = checklistService.createChecklist(createChecklistReqDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checklistRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @Operation(summary = "Card의 모든 Checklist 조회", description = "cardId를 가진 card의 checklist를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드의 모든 체크리스트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/card/{cardId}/checklists")
    public ResponseEntity<ApiResponse> findAllChecklist(@PathVariable Long cardId) {

        List<ChecklistRespDto> checklistRespDtoList = cardService.findAllChecklistByCardId(cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checklistRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Checklist 조회", description = "checklistId를 가진 checklist를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "체크리스트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/checklist/{checklistId}")
    public ResponseEntity<ApiResponse> findChecklistById(@PathVariable Long checklistId) {

        ChecklistRespDto checklistRespDto = checklistService.findChecklistById(checklistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checklistRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Checklist 정보 수정", description = "checklistId를 가진 checklist의 progress를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "체크리스트 progress 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/checklist/{checklistId}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable Long checklistId, @Valid @RequestBody UpdateChecklistProgressDto updateChecklistProgressDto) {

        updateChecklistProgressDto = checklistService.updateProgress(updateChecklistProgressDto, checklistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateChecklistProgressDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Checklist 정보 수정", description = "checklistId를 가진 checklist의 제목을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "체크리스트 제목 업데이트 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/checklist/{checklistId}/title")
    public ResponseEntity<?> updateTitle(
            @PathVariable Long checklistId, @Valid @RequestBody UpdateChecklistTitleDto updateChecklistTitleDto) {

        updateChecklistTitleDto = checklistService.updateTitle(updateChecklistTitleDto, checklistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateChecklistTitleDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Checklist 삭제", description = "checklistId를 가진 checklist를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "체크리스트 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/checklist/{checklistId}")
    public ResponseEntity<?> deleteChecklistById(@PathVariable Long checklistId) {

        checklistService.deleteChecklistById(checklistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
