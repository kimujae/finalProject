package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetupEntity;
import com.kujproject.kuj.domain.meetingTimeCalculator.TimeSetup_UserEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TimeSetup_UserDao extends Repository<TimeSetup_UserEntity, Long> {
    TimeSetup_UserEntity save(TimeSetup_UserEntity timeSetupUserEntity);
    List<TimeSetup_UserEntity> findAllByTimeSetup(TimeSetupEntity timeSetupEntity);
    List<TimeSetup_UserEntity> findAllByUser(UserEntity userEntity);
}
