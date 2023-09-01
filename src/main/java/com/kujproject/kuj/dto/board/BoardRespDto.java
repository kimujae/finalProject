package com.kujproject.kuj.dto.board;

import com.kujproject.kuj.domain.board.BoardEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
@Hidden
public class BoardRespDto {
    private String boardTitle;
    private String cover;
    private boolean isPublic;

    public static BoardRespDtoBuilder convertedBy(BoardEntity boardEntity) {
        return BoardRespDto.builder()
                .boardTitle(boardEntity.getTitle())
                .cover(boardEntity.getCover())
                .isPublic(boardEntity.isPublic());
    }
}
