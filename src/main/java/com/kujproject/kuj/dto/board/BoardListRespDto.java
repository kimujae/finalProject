package com.kujproject.kuj.dto.board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class BoardListRespDto {
    private Long boardId;
    private String boardName;
}
