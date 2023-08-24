package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetupEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface TimeSetupDao extends Repository<TimeSetupEntity, String> {
    TimeSetupEntity save(TimeSetupEntity TImeSetupEntity);
    Optional<TimeSetupEntity> findByTimeSetupId(Long timeSetupId);
    int deleteByTimeSetupId(Long timeSetupId);
}
