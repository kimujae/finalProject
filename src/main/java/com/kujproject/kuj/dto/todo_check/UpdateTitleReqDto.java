package com.kujproject.kuj.dto.todo_check;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTitleReqDto {
    @Size(max = 30, message = "제목은 30자를 초과할 수 없습니다.")
    @NotEmpty
    private String title;
}
