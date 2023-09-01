package com.kujproject.kuj.dto.board;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@Hidden
public class BoardListRespDto {
    private Long boardId;
    private String boardName;
}
