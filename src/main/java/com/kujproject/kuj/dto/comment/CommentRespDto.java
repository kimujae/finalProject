package com.kujproject.kuj.dto.comment;

import com.kujproject.kuj.domain.comment.CommentEntity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(builderMethodName = "builder")
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
