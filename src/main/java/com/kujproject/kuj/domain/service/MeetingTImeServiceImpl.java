package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.meeting_time.MeetingTimeEntity;
import com.kujproject.kuj.domain.repository.MeetingTimeDao;
import com.kujproject.kuj.dto.meeting_time.CreateMeetingTimeReqDto;
import com.kujproject.kuj.dto.meeting_time.MeetingTimeRespDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingTImeServiceImpl implements MeetingTimeService{
    private final MeetingTimeDao meetingTimeDao;

    public MeetingTImeServiceImpl(MeetingTimeDao meetingTimeDao) {
        this.meetingTimeDao = meetingTimeDao;
    }


    @Override
    public MeetingTimeRespDto createMeetingTime(CreateMeetingTimeReqDto createMeetingTimeReqDto) {
        MeetingTimeEntity meetingTimeEntity = MeetingTimeEntity.convertedBy(createMeetingTimeReqDto);
        meetingTimeDao.save(meetingTimeEntity);

        MeetingTimeRespDto meetingTimeRespDto = MeetingTimeRespDto.convertedBy(meetingTimeEntity);
        return meetingTimeRespDto;
    }

    @Override
    public MeetingTimeRespDto findByMeetingId(Long meetingId) {
        Optional<MeetingTimeEntity> meetingTimeEntity = meetingTimeDao.findByMeetingId(meetingId);
        MeetingTimeEntity meetingTime = meetingTimeEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND));

        return MeetingTimeRespDto.convertedBy(meetingTime);
    }

    @Override
    public void deleteByMeetingId(Long meetingId) {
        int deletedCount = meetingTimeDao.deleteByMeetingId(meetingId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.MEETINGTIME_NOT_FOUND);
        }
    }
}
