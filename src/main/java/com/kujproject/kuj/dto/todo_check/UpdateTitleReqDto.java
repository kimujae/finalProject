package com.kujproject.kuj.dto.todo_check;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTitleReqDto {
    @Size(max = 30, message = "제목은 30자를 초과할 수 없습니다.")
    @NotEmpty
    String title;
}
