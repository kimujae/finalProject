package com.kujproject.kuj.dto.meetingTimeCalculator;


import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUnableTimeSlotDto {
    List<@Valid List<@Valid Times>> times;
}
