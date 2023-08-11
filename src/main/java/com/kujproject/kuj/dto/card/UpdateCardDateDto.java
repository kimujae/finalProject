package com.kujproject.kuj.dto.card;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCardDateDto {
    @Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "유효하지 않은 날짜 포맷입니다.")
    LocalDate startdate;
    @Pattern(regexp = "^([01]?\\d|2[0-3]):[0-5]\\d:[0-5]\\d$", message = "유효하지 않은 날짜 포맷입니다.")
    LocalDate duedate;
}
