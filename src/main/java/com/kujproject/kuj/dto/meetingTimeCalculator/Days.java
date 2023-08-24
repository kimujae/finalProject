package com.kujproject.kuj.dto.meetingTimeCalculator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Days {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    String day;
}
