package com.kujproject.kuj.dto.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBoardTitleDto {
    @NotEmpty
    @Size(max = 20, message = "보드 제목은 20자를 초과할 수 없습니다.")
    String title;
}
