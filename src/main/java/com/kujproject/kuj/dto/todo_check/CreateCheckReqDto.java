package com.kujproject.kuj.dto.todo_check;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCheckReqDto {
    @Size(max = 30, message = "제목은 30자를 초과할 수 없습니다.")
    @NotEmpty
    String title;
    @Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "유효하지 않은 날짜 포맷입니다.")
    LocalDate duedate;
    boolean isCompleted;
    String userId;
}
