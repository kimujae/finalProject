package com.kujproject.kuj.dto.comment;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateCommentReqDto {
    @NotEmpty
    @Size(max = 200, message = "댓글 내용은 200자를 초과할 수 없습니다.")
    private String content;
    private String userId; //session정보로 리팩토링
}
