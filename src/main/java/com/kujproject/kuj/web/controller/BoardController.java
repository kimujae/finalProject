package com.kujproject.kuj.web.controller;


import com.kujproject.kuj.domain.service.BoardService;
import com.kujproject.kuj.dto.board.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/board")
    public ResponseEntity<?> createBoard(
            @Valid @RequestBody CreateBoardReqDto createBoardReqDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        boardService.createNewBoard(createBoardReqDto);
        BoardRespDto boardRespDto = new BoardRespDto();

        return ResponseEntity.ok().body(createBoardReqDto); // respDto로 반환?
    }


    @GetMapping("/board/{id}")
    public ResponseEntity<?> findBoardByBoardId(@PathVariable Long id) {
        BoardRespDto foundBoard = boardService.findBoardByBoardId(id);

        if(foundBoard != null) {
            return ResponseEntity.ok().body(foundBoard);
        }
        else {
            return ResponseEntity.noContent().build();
        }
    }

    @PatchMapping("/board/{id}/cover")
    public ResponseEntity<?> updateCover(@PathVariable Long id,
                                           @Valid @RequestBody UpdateBoardCoverDto updateBoardCoverDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        boardService.updateCover(id, updateBoardCoverDto);
        return ResponseEntity.ok().body(updateBoardCoverDto);
    }

    @PatchMapping("/board/{id}/public")
    public ResponseEntity<?> updatePublicRange(@PathVariable Long id,
                                               @Valid @RequestBody UpdateBoardPubRangeDto updateBoardPubRangeDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        boardService.updatePubRange(id, updateBoardPubRangeDto);
        return ResponseEntity.ok().body(updateBoardPubRangeDto);
    }

    @PatchMapping("/board/{id}/title")
    public ResponseEntity<?> updateTitle(@PathVariable Long id,
                                         @Valid @RequestBody UpdateBoardTitleDto updateBoardTitleDto, BindingResult bindingResult) {
        // bindingResult 에러 검출
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        boardService.updateTitle(id, updateBoardTitleDto);
        return ResponseEntity.ok().body(updateBoardTitleDto);
    }


    @DeleteMapping("/board/{id}")
    public ResponseEntity<Boolean> deleteBoardById(@PathVariable Long id) {
        boolean isDeleted = boardService.deleteBoard(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

}