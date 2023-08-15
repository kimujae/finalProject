package com.kujproject.kuj.domain.meeting_time;

import com.kujproject.kuj.dto.meeting_time.CreateMeetingTimeReqDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Builder(builderMethodName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MeetingTimeEntity {
    @Id
    Long meetingId;
    LocalTime startTime;
    LocalTime endTime;
    LocalDate startDay;
    LocalDate endDay;


    public static MeetingTimeEntity convertedBy(CreateMeetingTimeReqDto createMeetingTimeReqDto) {
        return MeetingTimeEntity.builder()
                .startTime(createMeetingTimeReqDto.getStartTime())
                .endTime(createMeetingTimeReqDto.getEndTime())
                .startDay(createMeetingTimeReqDto.getStartDay())
                .endDay(createMeetingTimeReqDto.getEndDay())
                .build();
    }
}
