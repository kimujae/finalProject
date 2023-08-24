package com.kujproject.kuj.dto.meetingTimeCalculator;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Times {
    @DateTimeFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$", message = "알맞은 시간 형식을 입력해주세요.")
    String startTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Pattern(regexp = "^(?:[01]\\d|2[0-3]):[0-5]\\d$",  message = "알맞은 시간 형식을 입력해주세요.")
    String endTime;
}