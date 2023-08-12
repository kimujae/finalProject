package com.kujproject.kuj.dto.todo_check;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDateReqDto {
    @Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "유효하지 않은 날짜 포맷입니다.")
    LocalDate duedate;
}
