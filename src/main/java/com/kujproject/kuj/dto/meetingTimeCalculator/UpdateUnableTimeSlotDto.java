package com.kujproject.kuj.dto.meetingTimeCalculator;


import jakarta.validation.Valid;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateUnableTimeSlotDto {
    private List<@Valid List<@Valid Times>> times;
}
