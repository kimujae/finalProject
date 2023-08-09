package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.domain.service.TodoCheckService;
import com.kujproject.kuj.dto.todo_check.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class TodoCheckController {
    private final TodoCheckService todoCheckService;
    private final ChecklistService checklistService;


    public TodoCheckController(TodoCheckService todoCheckService, ChecklistService checklistService) {
        this.todoCheckService = todoCheckService;
        this.checklistService = checklistService;
    }


    @PostMapping("/checklist/{id}/todoCheck")
    public ResponseEntity<CreateCheckReqDto> createCheck(
            @PathVariable Long id, @Valid @RequestBody  CreateCheckReqDto createCheckReqDto) {

        CreateCheckReqDto checkReqDto = todoCheckService.createCheck(createCheckReqDto, id);
        if(checkReqDto != null) {
            return ResponseEntity.ok().body(createCheckReqDto);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/checklist/{id}/todoCheck")
    public ResponseEntity<List<CheckRespDto>> findAllCheckByChecklistId(@PathVariable Long id) {
        List<CheckRespDto> todoCheckList = checklistService.findAllCheckByChecklistID(id);

        if(todoCheckList != null) {
            return ResponseEntity.ok().body(todoCheckList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("todoCheck/{id}")
    public ResponseEntity<CheckRespDto> findCheckByCheckId(@PathVariable Long id) {
        CheckRespDto checkRespDto = todoCheckService.findCheckById(id);

        if(checkRespDto != null) {
            return ResponseEntity.ok().body(checkRespDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/todoCheck/{id}/completed")
    public ResponseEntity<?> updateCompleted(
            @PathVariable Long id, @Valid @RequestBody UpdateCompletedReqDto updateCompletedReqDto) {
        todoCheckService.updateCompleted(updateCompletedReqDto, id);
        return ResponseEntity.ok().body(updateCompletedReqDto);
    }

    @PatchMapping("/todoCheck/{id}/duedate")
    public ResponseEntity<?> updateDate(
            @PathVariable Long id, @Valid @RequestBody UpdateDateReqDto updateDateReqDto) {
        todoCheckService.updateDate(updateDateReqDto, id);
        return ResponseEntity.ok().body(updateDateReqDto);
    }

    @PatchMapping("/todoCheck/{id}/title")
    public ResponseEntity<?> updateTitle(
            @PathVariable Long id, @Valid @RequestBody UpdateTitleReqDto updateTitleReqDto) {
        todoCheckService.updateTitle(updateTitleReqDto, id);
        return ResponseEntity.ok().body(updateTitleReqDto);
    }


    @DeleteMapping("todoCheck/{id}")
    public ResponseEntity<HttpStatus> deleteCheckById(@PathVariable Long id) {
        boolean isDeleted;
        isDeleted = todoCheckService.deleteCheckById(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
