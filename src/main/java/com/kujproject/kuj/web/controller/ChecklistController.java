package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateProgressReqDto;
import com.kujproject.kuj.dto.checklist.UpdateTitleReqDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
            @PathVariable Long id, CreateChecklistReqDto createChecklistReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        checklistService.createChecklist(createChecklistReqDto, id);
        return null;
    }

    @GetMapping("/card/{id}/checklist")
    public ResponseEntity<?> findAllChecklist(@PathVariable Long id) {
        List<ChecklistEntity> checklistEntityList = cardService.findAllChecklistByCardId(id);

        if(checklistEntityList != null) {
            ResponseEntity.ok().body(checklistEntityList);
        }
        return null;
    }

    @PatchMapping("/checklist/{id}/progress")
    public ResponseEntity<?> updateProgress(
            @PathVariable Long id, UpdateProgressReqDto updateProgressReqDto, BindingResult bindingResult) {
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
            @PathVariable Long id, UpdateTitleReqDto updateTitleReqDto, BindingResult bindingResult) {
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
    public ResponseEntity<HttpStatus> deleteChecklistById(@PathVariable Long id) {
        boolean isDeleted = checklistService.deleteChecklistById(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
