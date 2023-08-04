package com.kujproject.kuj.dto.comment;

import lombok.Data;

@Data
public class CommentRespDto {
    String content;
    String userId; //세션정보로 fix
}
