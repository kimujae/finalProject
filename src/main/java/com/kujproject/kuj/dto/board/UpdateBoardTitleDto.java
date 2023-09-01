package com.kujproject.kuj.dto.board;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
@Hidden
public class UpdateBoardTitleDto {
    @NotEmpty
    @Size(max = 20, message = "보드 제목은 20자를 초과할 수 없습니다.")
    private String title;
}
