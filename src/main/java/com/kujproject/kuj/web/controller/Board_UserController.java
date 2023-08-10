package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.BoardService;
import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.dto.board.BoardRespDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Board_UserController {
    private final BoardService boardService;
    private final UserService userService;

    public Board_UserController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }


    @GetMapping("/board/{id}/members")
    public ResponseEntity<ApiResponse> findBoardMembers(@PathVariable Long id) {

        List<UserRespDto> members = boardService.findMemberByBoardId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(members)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @GetMapping("user/{id}/boards")
    public ResponseEntity<ApiResponse> findUsersBoard(@PathVariable String id) {

        List<BoardRespDto> boards = userService.findUsersBoard(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(boards)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }
}
