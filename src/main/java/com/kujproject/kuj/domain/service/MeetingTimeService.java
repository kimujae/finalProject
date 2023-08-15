package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.meeting_time.CreateMeetingTimeReqDto;
import com.kujproject.kuj.dto.meeting_time.MeetingTimeRespDto;

public interface MeetingTimeService {
    MeetingTimeRespDto createMeetingTime(CreateMeetingTimeReqDto createMeetingTimeReqDto);
    MeetingTimeRespDto findByMeetingId(Long meetingId);
    void deleteByMeetingId(Long meetingId);
}
