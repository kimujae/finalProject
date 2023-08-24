package com.kujproject.kuj.dto.meetingTimeCalculator;

import com.kujproject.kuj.web.common.utils.MeetingTimeDisjointSet;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class MeetingTimeResultRespDto {
    Days[] days;
    List<List<LocalTime>> times;
    List<MeetingTimeDisjointSet> resultSet;
}
