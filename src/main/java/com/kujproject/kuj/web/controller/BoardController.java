package com.kujproject.kuj.web.controller;


import com.kujproject.kuj.domain.service.BoardService;
import com.kujproject.kuj.dto.board.*;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/board")
    public ResponseEntity<ApiResponse> createBoard(
            @Valid @RequestBody CreateBoardReqDto createBoardReqDto) {

        boardService.createNewBoard(createBoardReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                        .result(createBoardReqDto)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build(), HttpStatus.OK);
    }


    @GetMapping("/board/{id}")
    public ResponseEntity<ApiResponse> findBoardByBoardId(@PathVariable Long id) {

        BoardRespDto foundBoard = boardService.findBoardByBoardId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(foundBoard)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/board/{id}/cover")
    public ResponseEntity<ApiResponse> updateCover(@PathVariable Long id,
                                           @Valid @RequestBody UpdateBoardCoverDto updateBoardCoverDto) {

        updateBoardCoverDto = boardService.updateCover(id, updateBoardCoverDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateBoardCoverDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/board/{id}/public")
    public ResponseEntity<ApiResponse> updatePublicRange(@PathVariable Long id,
                                               @Valid @RequestBody UpdateBoardPubRangeDto updateBoardPubRangeDto) {

        updateBoardPubRangeDto = boardService.updatePubRange(id, updateBoardPubRangeDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateBoardPubRangeDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @PatchMapping("/board/{id}/title")
    public ResponseEntity<ApiResponse> updateTitle(@PathVariable Long id,
                                         @Valid @RequestBody UpdateBoardTitleDto updateBoardTitleDto) {

        updateBoardTitleDto = boardService.updateTitle(id, updateBoardTitleDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateBoardTitleDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/board/{id}")
    public ResponseEntity<ApiResponse> deleteBoardById(@PathVariable Long id) {

        boardService.deleteBoard(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}