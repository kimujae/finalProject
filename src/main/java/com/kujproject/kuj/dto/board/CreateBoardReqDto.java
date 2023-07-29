package com.kujproject.kuj.dto.board;

import lombok.Data;

@Data
public class CreateBoardReqDto {
    // 필수
    String title;
    //기본값
    String cover;
    //기본값
    boolean isPublic;
}
