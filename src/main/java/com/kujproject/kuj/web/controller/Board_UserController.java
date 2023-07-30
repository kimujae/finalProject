package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.service.BoardService;
import com.kujproject.kuj.domain.service.UserService;
import com.kujproject.kuj.domain.user.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class Board_UserController {
    private final BoardService boardService;
    private final UserService userService;

    public Board_UserController(BoardDao boardDao, BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }


    @GetMapping("/board/{id}/members")
    public ResponseEntity<List<UserEntity>> findBoardMembers(@PathVariable Long id) {
        List<UserEntity> members = boardService.findMemberByBoardId(id);

        if(members != null) {
            return ResponseEntity.ok().body(members);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("user/{id}/boards")
    public ResponseEntity<List<BoardEntity>> findUsersBoard(@PathVariable String id) {
        List<BoardEntity> boards = userService.findUsersBoard(id);

        if(boards != null) {
            return ResponseEntity.ok().body(boards);
        }

        return ResponseEntity.notFound().build();
    }
}
