package com.kujproject.kuj.dto.comment;

import com.kujproject.kuj.domain.comment.CommentEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
@Hidden
public class CommentRespDto {
    String content;
    String userId; //세션정보로 fix

    public static CommentRespDto convertedBy(CommentEntity comment, String userId) {
        return CommentRespDto.builder()
                .content(comment.getContent())
                .userId(userId)
                .build();
    }
}
