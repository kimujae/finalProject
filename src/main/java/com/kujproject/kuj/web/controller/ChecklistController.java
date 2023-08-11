package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistProgressDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistTitleDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChecklistController {
    private final ChecklistService checklistService;
    private final CardService cardService;

    public ChecklistController(ChecklistService checklistService, CardService cardService) {
        this.checklistService = checklistService;
        this.cardService = cardService;
    }

    @PostMapping("/card/{id}/checklist")
    public ResponseEntity<ApiResponse> createChecklist(
            @PathVariable Long id, @Valid @RequestBody CreateChecklistReqDto createChecklistReqDto) {

        ChecklistRespDto checklistRespDto = checklistService.createChecklist(createChecklistReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checklistRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    /*
        List<Entity> 를 반환하려하면 json 직렬화 문제 발생 -> 무한루프 -> 해결방안은 찾아야된다.
        따라서 해결책으로 dto변환을 사용 -> 비용발생
        어떻게 해결하는 것이 나을까?
     */
    @GetMapping("/card/{id}/checklist")
    public ResponseEntity<ApiResponse> findAllChecklist(@PathVariable Long id) {

        List<ChecklistRespDto> checklistRespDtoList = cardService.findAllChecklistByCardId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checklistRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @GetMapping("/checklist/{id}")
    public ResponseEntity<ApiResponse> findChecklistById(@PathVariable Long id) {

        ChecklistRespDto checklistRespDto = checklistService.findChecklistById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checklistRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/checklist/{id}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable Long id, @Valid @RequestBody UpdateChecklistProgressDto updateChecklistProgressDto) {

        updateChecklistProgressDto = checklistService.updateProgress(updateChecklistProgressDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateChecklistProgressDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/checklist/{id}/title")
    public ResponseEntity<?> updateTitle(
            @PathVariable Long id, @Valid @RequestBody UpdateChecklistTitleDto updateChecklistTitleDto) {

        updateChecklistTitleDto = checklistService.updateTitle(updateChecklistTitleDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateChecklistTitleDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/checklist/{id}")
    public ResponseEntity<?> deleteChecklistById(@PathVariable Long id) {

        checklistService.deleteChecklistById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
