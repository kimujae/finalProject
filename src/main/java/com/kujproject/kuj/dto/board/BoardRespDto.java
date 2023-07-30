package com.kujproject.kuj.dto.board;

import lombok.Data;

@Data
public class BoardRespDto {
    String boardTitle;
    String cover;
    boolean isPublic;
}
