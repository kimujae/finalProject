package com.kujproject.kuj.dto.checklist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateChecklistTitleDto {
    @NotEmpty
    @Size(max = 15, message = "체크리스트 제목은 15자를 초과할 수 없습니다.")
    String title;
}
