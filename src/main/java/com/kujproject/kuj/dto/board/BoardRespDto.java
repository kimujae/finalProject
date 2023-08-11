package com.kujproject.kuj.dto.board;

import com.kujproject.kuj.domain.board.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "builder")
@Getter
@AllArgsConstructor
public class BoardRespDto {
    String boardTitle;
    String cover;
    boolean isPublic;

    public static BoardRespDtoBuilder convertedBy(BoardEntity boardEntity) {
        return BoardRespDto.builder()
                .boardTitle(boardEntity.getTitle())
                .cover(boardEntity.getCover())
                .isPublic(boardEntity.isPublic());
    }
}
