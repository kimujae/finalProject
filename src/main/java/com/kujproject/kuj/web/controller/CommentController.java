package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.CardService;
import com.kujproject.kuj.domain.service.CommentService;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;
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
    public ResponseEntity<?> createComment(
            @PathVariable Long id, @Valid @RequestBody CreateCommentReqDto createCommentReqDto, BindingResult bindingResult) {

        createCommentReqDto = commentService.createComment(createCommentReqDto, id);

        if (createCommentReqDto != null) {
            return ResponseEntity.ok().body(createCommentReqDto);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/card/{id}/comment")
    public ResponseEntity<List<CommentRespDto>> findAllCommentByCardId(@PathVariable Long id) {
        List<CommentRespDto> commentRespDtoList = cardService.findAllCommentByCardId(id);

        if(commentRespDtoList != null) {
            return ResponseEntity.ok().body(commentRespDtoList);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<CommentRespDto> findCommentByCommentId(@PathVariable Long id) {
        CommentRespDto commentRespDto = commentService.findCommentById(id);

        if(commentRespDto != null) {
            return ResponseEntity.ok().body(commentRespDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("comment/{id}/content")
    public ResponseEntity<?> updateContent(
            @PathVariable Long id, @Valid @RequestBody UpdateContentReqDto updateContentReqDto, BindingResult bindingResult) {
        updateContentReqDto = commentService.updateContent(updateContentReqDto, id);

        if(updateContentReqDto != null) {
            return ResponseEntity.ok().body(updateContentReqDto);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/comment/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        boolean isDeleted = commentService.deleteComment(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
