package com.kujproject.kuj.domain.meetingTimeCalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kujproject.kuj.dto.meetingTimeCalculator.InitialTimeSetupReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Builder(builderMethodName = "builder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "meeting")
public class TimeSetupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeSetupId;
    @Column(columnDefinition = "TEXT")
    private String days;
    @Column(columnDefinition = "TEXT")
    private String times;
    @Column(columnDefinition = "TEXT")
    private String targetTimes;


    @OneToMany
    private List<UnableTimeSlotEntity> timeTable;

    public static TimeSetupEntity convertedBy(InitialTimeSetupReqDto initialTimeSetupReqDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return TimeSetupEntity.builder()
                .days(objectMapper.writeValueAsString(initialTimeSetupReqDto.getDays()))
                .times(objectMapper.writeValueAsString(initialTimeSetupReqDto.getTimes()))
                .targetTimes(objectMapper.writeValueAsString(initialTimeSetupReqDto.getTargetTimes()))
                .build();
    }
}
