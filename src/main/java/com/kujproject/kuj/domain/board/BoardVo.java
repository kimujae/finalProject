package com.kujproject.kuj.domain.board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardVo {
    Long boardId;
    String title;
    String isPublic;
}
