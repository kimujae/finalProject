package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.CommentService;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Comment CRUD API입니다.")
public class CommentController {
    private final CommentService commentService;
    private final CardService cardService;


    @Operation(summary = "Card에 Comment 생성", description = "cardId를 가진 card에 Comment 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드에 코멘트 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/card/{cardId}/comment")
    public ResponseEntity<ApiResponse> createComment(
            @PathVariable Long cardId, @Valid @RequestBody CreateCommentReqDto createCommentReqDto) {

        CommentRespDto commentRespDto = commentService.createComment(createCommentReqDto, cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(commentRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @Operation(summary = "Card의 모든 Comment 조회", description = "cardId를 가진 card의 모든 Comment 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "카드의 모든 코멘트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/card/{cardId}/comments")
    public ResponseEntity<ApiResponse> findAllCommentByCardId(@PathVariable Long cardId) {

        List<CommentRespDto> commentRespDtoList = cardService.findAllCommentByCardId(cardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(commentRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Comment 조회", description = "commentId를 가진 Comment 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "코멘트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> findCommentByCommentId(@PathVariable Long commentId) {

        CommentRespDto commentRespDto = commentService.findCommentById(commentId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(commentRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Comment 정보 수정", description = "commentId를 가진 Comment의 내용을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "코멘트 내용 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("comment/{commentId}/content")
    public ResponseEntity<?> updateContent(
            @PathVariable Long commentId, @Valid @RequestBody UpdateContentReqDto updateContentReqDto) {

        updateContentReqDto = commentService.updateContent(updateContentReqDto, commentId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateContentReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }



    @Operation(summary = "Comment 삭제", description = "commentId를 가진 Comment를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "코멘트 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId) {

        commentService.deleteComment(commentId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
