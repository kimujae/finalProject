package com.kujproject.kuj.dto.meetingTimeCalculator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class TargetTime {
    @Min(value = 1)
    @Max(value = 23)
    int targetTime;
}
