package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.domain.service.TodoCheckService;
import com.kujproject.kuj.dto.todo_check.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
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
    public ResponseEntity<ApiResponse> createCheck(
            @PathVariable Long id, @Valid @RequestBody  CreateCheckReqDto createCheckReqDto) {

        CheckRespDto checkRespDto = todoCheckService.createCheck(createCheckReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checkRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @GetMapping("/checklist/{id}/todoCheck")
    public ResponseEntity<ApiResponse> findAllCheckByChecklistId(@PathVariable Long id) {

        List<CheckRespDto> todoCheckList = checklistService.findAllCheckByChecklistID(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(todoCheckList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @GetMapping("todoCheck/{id}")
    public ResponseEntity<ApiResponse> findCheckByCheckId(@PathVariable Long id) {

        CheckRespDto checkRespDto = todoCheckService.findCheckById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checkRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/todoCheck/{id}/completed")
    public ResponseEntity<ApiResponse> updateCompleted(
            @PathVariable Long id, @Valid @RequestBody UpdateCompletedReqDto updateCompletedReqDto) {

        updateCompletedReqDto = todoCheckService.updateCompleted(updateCompletedReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCompletedReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/todoCheck/{id}/duedate")
    public ResponseEntity<ApiResponse> updateDate(
            @PathVariable Long id, @Valid @RequestBody UpdateDateReqDto updateDateReqDto) {

        updateDateReqDto = todoCheckService.updateDate(updateDateReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateDateReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/todoCheck/{id}/title")
    public ResponseEntity<ApiResponse> updateTitle(
            @PathVariable Long id, @Valid @RequestBody UpdateTitleReqDto updateTitleReqDto) {

        updateTitleReqDto = todoCheckService.updateTitle(updateTitleReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateTitleReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("todoCheck/{id}")
    public ResponseEntity<ApiResponse> deleteCheckById(@PathVariable Long id) {

        todoCheckService.deleteCheckById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
