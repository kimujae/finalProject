package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.ChecklistService;
import com.kujproject.kuj.domain.service.TodoCheckService;
import com.kujproject.kuj.dto.todo_check.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@Tag(name = "ToDo", description = "ToDo CRUD API입니다.")
public class TodoCheckController {
    private final TodoCheckService todoCheckService;
    private final ChecklistService checklistService;


    @Operation(summary = "Checklist에 ToDo 생성", description = "checklistId를 가진 checklist의 ToDo를 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Checklist에 ToDo 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/checklist/{checklistId}/todo")
    public ResponseEntity<ApiResponse> createToDo(
            @PathVariable Long checklistId, @Valid @RequestBody  CreateCheckReqDto createCheckReqDto) {

        CheckRespDto checkRespDto = todoCheckService.createCheck(createCheckReqDto, checklistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checkRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @Operation(summary = "Checklist의 모든ToDo 조회", description = "checklistId를 가진 checklist의 모든 ToDo를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "체크리스트의 모든 ToDo 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/checklist/{checklistId}/todos")
    public ResponseEntity<ApiResponse> findAllToDosByChecklistId(@PathVariable Long checklistId) {

        List<CheckRespDto> todoCheckList = checklistService.findAllCheckByChecklistID(checklistId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(todoCheckList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "ToDo 조회", description = "todoId를 가진 ToDo를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ToDo 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("todo/{todoId}")
    public ResponseEntity<ApiResponse> findToDoByCheckId(@PathVariable Long todoId) {

        CheckRespDto checkRespDto = todoCheckService.findCheckById(todoId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(checkRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "ToDo 정보 수정", description = "todoId를 가진 ToDo의 완료여부를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ToDo 완료여부 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/todo/{todoId}/completed")
    public ResponseEntity<ApiResponse> updateCompleted(
            @PathVariable Long todoId, @Valid @RequestBody UpdateCompletedReqDto updateCompletedReqDto) {

        updateCompletedReqDto = todoCheckService.updateCompleted(updateCompletedReqDto, todoId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateCompletedReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "ToDo 정보 수정", description = "todoId를 가진 ToDo의 기한을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ToDo 기한 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/todo/{todoId}/duedate")
    public ResponseEntity<ApiResponse> updateDate(
            @PathVariable Long todoId, @Valid @RequestBody UpdateDateReqDto updateDateReqDto) {

        updateDateReqDto = todoCheckService.updateDate(updateDateReqDto, todoId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateDateReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "ToDo 정보 수정", description = "todoId를 가진 ToDo의 제목을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "ToDo 제목 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/todo/{todoId}/title")
    public ResponseEntity<ApiResponse> updateTitle(
            @PathVariable Long todoId, @Valid @RequestBody UpdateTitleReqDto updateTitleReqDto) {

        updateTitleReqDto = todoCheckService.updateTitle(updateTitleReqDto, todoId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateTitleReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "ToDo 삭제", description = "todoId를 가진 ToDo를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "ToDo 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("todo/{todoId}")
    public ResponseEntity<ApiResponse> deleteToDoById(@PathVariable Long todoId) {

        todoCheckService.deleteCheckById(todoId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
