package com.kujproject.kuj.web.controller;


import com.kujproject.kuj.domain.service.BoardService;
import com.kujproject.kuj.dto.board.*;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "BOARD", description = "BOARD CRUD API입니다.")
public class BoardController {
    private final BoardService boardService;


    @Operation(summary = "Board생성", description = "Board를 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/board")
    public ResponseEntity<ApiResponse> createBoard(
            @Valid @RequestBody CreateBoardReqDto createBoardReqDto) {

        boardService.createBoard(createBoardReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                        .result(createBoardReqDto)
                        .successCode(SuccessCode.INSERT_SUCCESS)
                        .build(), HttpStatus.OK);
    }


    @Operation(summary = "Board조회", description = "BoardId로 Board를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/board/{boardId}")
    public ResponseEntity<ApiResponse> findBoardByBoardId(@PathVariable Long boardId) {

        BoardRespDto foundBoard = boardService.findBoardByBoardId(boardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(foundBoard)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Board 정보 수정", description = "BoardId를 가진 Board의 커버를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드 커버 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/board/{boardId}/cover")
    public ResponseEntity<ApiResponse> updateCover(@PathVariable Long boardId,
                                           @Valid @RequestBody UpdateBoardCoverDto updateBoardCoverDto) {

        updateBoardCoverDto = boardService.updateCover(boardId, updateBoardCoverDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateBoardCoverDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @Operation(summary = "Board 정보 수정", description = "BoardId를 가진 Board의 공개범위를 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드 공개범위 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/board/{boardId}/public")
    public ResponseEntity<ApiResponse> updatePublicRange(@PathVariable Long boardId,
                                               @Valid @RequestBody UpdateBoardPubRangeDto updateBoardPubRangeDto) {

        updateBoardPubRangeDto = boardService.updatePubRange(boardId, updateBoardPubRangeDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateBoardPubRangeDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Board 정보 수정", description = "BoardId를 가진 Board의 제목을 수정합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드 제목 수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PatchMapping("/board/{boardId}/title")
    public ResponseEntity<ApiResponse> updateTitle(@PathVariable Long boardId,
                                         @Valid @RequestBody UpdateBoardTitleDto updateBoardTitleDto) {

        updateBoardTitleDto = boardService.updateTitle(boardId, updateBoardTitleDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateBoardTitleDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Board 식제", description = "BoardId를 가진 Board를 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "보드 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<ApiResponse> deleteBoardById(@PathVariable Long boardId) {

        boardService.deleteBoard(boardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "Board 정보 조회", description = "BoardId를 가진 Board의 모든 참여 유저를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "보드 참여 유저 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/board/{boardId}/users")
    public ResponseEntity<ApiResponse> findInvitedUsers(@PathVariable Long boardId) {

        List<UserRespDto> members = boardService.findMembersByBoardId(boardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(members)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "Board 목록 조회", description = "userId를 가진 User의 모든 보드 목록을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "유저의 보드 목록 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("user/{userId}/boards")
    public ResponseEntity<ApiResponse> findUserBoards(@PathVariable String userId) {

        List<BoardRespDto> boards = boardService.findUsersBoard(userId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(boards)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }
}