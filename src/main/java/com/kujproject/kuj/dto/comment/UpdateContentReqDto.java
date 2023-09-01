package com.kujproject.kuj.dto.comment;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Hidden
public class UpdateContentReqDto {
    @Size(max = 200, message = "댓글 내용은 200자를 초과할 수 없습니다.")
    String content;
}
