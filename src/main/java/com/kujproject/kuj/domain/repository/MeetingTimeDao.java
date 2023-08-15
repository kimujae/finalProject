package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.meeting_time.MeetingTimeEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MeetingTimeDao extends Repository<MeetingTimeEntity, String> {
    MeetingTimeEntity save(MeetingTimeEntity meetingTimeEntity);
    Optional<MeetingTimeEntity> findByMeetingId(Long meetingId);
    int deleteByMeetingId(Long meetingId);
}
