package com.kujproject.kuj.dto.meetingTimeCalculator;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "builder")
@Getter
@AllArgsConstructor
public class TimeSetupRespDto {
    private String times;
    private String days;
    private String targetTimes;


    public static TimeSetupRespDto convertedBy(TimeSetupEntity meetingTimeEntity) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        json = mapper.writeValueAsString(meetingTimeEntity);
        TimeSetupEntity meetingTime = mapper.readValue(json, TimeSetupEntity.class);

        return TimeSetupRespDto.builder()
                .times(meetingTime.getTimes())
                .days(meetingTime.getDays())
                .targetTimes(meetingTime.getTargetTimes())
                .build();
    }
}
