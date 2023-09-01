package com.kujproject.kuj.dto.meetingTimeCalculator;

import com.kujproject.kuj.web.common.utils.MeetingTimeDisjointSet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class MeetingTimeResultRespDto {
    private Days[] days;
    private List<List<LocalTime>> times;
    private List<MeetingTimeDisjointSet> resultSet;
}
