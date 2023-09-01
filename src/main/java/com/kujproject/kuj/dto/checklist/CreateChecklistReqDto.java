package com.kujproject.kuj.dto.checklist;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
public class CreateChecklistReqDto {
    @NotEmpty
    @Size(max = 15, message = "체크리스트 제목은 15자를 초과할 수 없습니다.")
    private String title;
}
