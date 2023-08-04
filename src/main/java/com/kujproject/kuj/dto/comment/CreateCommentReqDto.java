package com.kujproject.kuj.dto.comment;

import lombok.Data;

@Data
public class CreateCommentReqDto {
    String content;
    String userId; //session정보로 리팩토링
}
