package com.kujproject.kuj.dto.meeting_time;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateMeetingTimeReqDto {
    LocalTime startTime;
    LocalTime endTime;
    LocalDate startDay;
    LocalDate endDay;

}
