package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateProgressReqDto;
import com.kujproject.kuj.dto.checklist.UpdateTitleReqDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChecklistController {
    private final ChecklistService checklistService;
    private final CardService cardService;

    public ChecklistController(ChecklistService checklistService, CardService cardService) {
        this.checklistService = checklistService;
        this.cardService = cardService;
    }

    @PostMapping("/card/{id}/checklist")
    public ResponseEntity<?> createChecklist(
            @PathVariable Long id, @Valid @RequestBody CreateChecklistReqDto createChecklistReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        checklistService.createChecklist(createChecklistReqDto, id);
        return ResponseEntity.ok().body(createChecklistReqDto);
    }


    /*
        List<Entity> 를 반환하려하면 json 직렬화 문제 발생 -> 무한루프 -> 해결방안은 찾아야된다.
        따라서 해결책으로 dto변환을 사용 -> 비용발생
        어떻게 해결하는 것이 나을까?
     */
    @GetMapping("/card/{id}/checklist")
    public ResponseEntity<List<ChecklistRespDto>> findAllChecklist(@PathVariable Long id) {
        List<ChecklistRespDto> checklistRespDtoList = cardService.findAllChecklistByCardId(id);

        if(checklistRespDtoList != null) {
            return ResponseEntity.ok().body(checklistRespDtoList);
        }
        return null;
    }

    @GetMapping("/checklist/{id}")
    public ResponseEntity<ChecklistRespDto> findChecklistById(@PathVariable Long id) {
        ChecklistRespDto checklistRespDto = checklistService.findChecklistById(id);

        if(checklistRespDto != null) {
            return ResponseEntity.ok().body(checklistRespDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/checklist/{id}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable Long id, @Valid @RequestBody UpdateProgressReqDto updateProgressReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        checklistService.updateProgress(updateProgressReqDto, id);
        return ResponseEntity.ok().body(updateProgressReqDto);
    }


    @PatchMapping("/checklist/{id}/title")
    public ResponseEntity<?> updateTitle(
            @PathVariable Long id, @Valid @RequestBody UpdateTitleReqDto updateTitleReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        checklistService.updateTitle(updateTitleReqDto, id);
        return ResponseEntity.ok().body(updateTitleReqDto);
    }


    @DeleteMapping("/checklist/{id}")
    public ResponseEntity<?> deleteChecklistById(@PathVariable Long id) {
        boolean isDeleted = checklistService.deleteChecklistById(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
