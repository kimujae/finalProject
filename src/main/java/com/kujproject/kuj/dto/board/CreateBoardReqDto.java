package com.kujproject.kuj.dto.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateBoardReqDto {
    @NotEmpty
    @Size(max = 20, message = "보드 제목은 20자를 초과할 수 없습니다.")
    String title;

    @Size(max = 7, message = "보드 커버는 7자를 초과할 수 없습니다.")
    @Pattern(regexp = "^#[0-9]{6}$")
    String cover = "#000000";

    @NotEmpty
    boolean isPublic = true;
}
