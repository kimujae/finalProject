package com.kujproject.kuj.dto.meetingTimeCalculator;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TargetTime {
    @Min(value = 1)
    @Max(value = 23)
    private int targetTime;
}
