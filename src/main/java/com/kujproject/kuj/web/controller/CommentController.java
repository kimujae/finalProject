package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.CommentService;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;
    private final CardService cardService;

    public CommentController(CommentService commentService, CardService cardService) {
        this.commentService = commentService;
        this.cardService = cardService;
    }


    @PostMapping("/card/{id}/comment")
    public ResponseEntity<ApiResponse> createComment(
            @PathVariable Long id, @Valid @RequestBody CreateCommentReqDto createCommentReqDto) {

        CommentRespDto commentRespDto = commentService.createComment(createCommentReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(commentRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/card/{id}/comment")
    public ResponseEntity<ApiResponse> findAllCommentByCardId(@PathVariable Long id) {

        List<CommentRespDto> commentRespDtoList = cardService.findAllCommentByCardId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(commentRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<ApiResponse> findCommentByCommentId(@PathVariable Long id) {

        CommentRespDto commentRespDto = commentService.findCommentById(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(commentRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("comment/{id}/content")
    public ResponseEntity<?> updateContent(
            @PathVariable Long id, @Valid @RequestBody UpdateContentReqDto updateContentReqDto) {

        updateContentReqDto = commentService.updateContent(updateContentReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateContentReqDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
