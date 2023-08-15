package com.kujproject.kuj.dto.meeting_time;


import com.kujproject.kuj.domain.meeting_time.MeetingTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder(builderMethodName = "builder")
@Getter
@AllArgsConstructor
public class MeetingTimeRespDto {
    LocalTime startTime;
    LocalTime endTime;
    LocalDate startDay;
    LocalDate endDay;

    public static MeetingTimeRespDto convertedBy(MeetingTimeEntity meetingTimeEntity) {
        return MeetingTimeRespDto.builder()
                .startTime(meetingTimeEntity.getStartTime())
                .endTime(meetingTimeEntity.getEndTime())
                .startDay(meetingTimeEntity.getStartDay())
                .endDay(meetingTimeEntity.getEndDay())
                .build();
    }
}
