package com.kujproject.kuj.domain.meetingTimeCalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kujproject.kuj.dto.meetingTimeCalculator.UpdateUnableTimeSlotDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "meeting_time_table")
@Builder(builderMethodName = "builder")
@AllArgsConstructor
@NoArgsConstructor
public class UnableTimeSlotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unableTimeSlotId;
    @Column(columnDefinition = "TEXT")
    private String exclusionTimes;


    @ManyToOne
    @JoinColumn(name = "time_setup_id")
    private TimeSetupEntity timeSetup;

    public static UnableTimeSlotEntity convertedBy(UpdateUnableTimeSlotDto updateUnableTimeSlotDto, TimeSetupEntity timeSetup) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return UnableTimeSlotEntity.builder()
                .exclusionTimes(objectMapper.writeValueAsString(updateUnableTimeSlotDto.getTimes()))
                .timeSetup(timeSetup)
                .build();
    }
}
