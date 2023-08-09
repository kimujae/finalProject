package com.kujproject.kuj.dto.board;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBoardCoverDto {
    @Size(max = 7, message = "보드 커버는 7자를 초과할 수 없습니다.")
    @Pattern(regexp = "^#[0-9]{6}$")
    String cover;
}
