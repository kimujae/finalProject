package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetupEntity;
import com.kujproject.kuj.domain.meetingTimeCalculator.UnableTimeSlotEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UnableTimeSlotDao extends Repository<UnableTimeSlotEntity, Long> {
    UnableTimeSlotEntity save(UnableTimeSlotEntity unableTimeSlotEntity);
    List<UnableTimeSlotEntity> findAllByTimeSetup(TimeSetupEntity timeSetupEntity);
}
