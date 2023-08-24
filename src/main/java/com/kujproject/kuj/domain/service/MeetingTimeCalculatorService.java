package com.kujproject.kuj.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kujproject.kuj.domain.meetingTimeCalculator.UnableTimeSlotEntity;
import com.kujproject.kuj.dto.meetingTimeCalculator.InitialTimeSetupReqDto;
import com.kujproject.kuj.dto.meetingTimeCalculator.TimeSetupRespDto;
import com.kujproject.kuj.dto.meetingTimeCalculator.MeetingTimeResultRespDto;
import com.kujproject.kuj.dto.meetingTimeCalculator.UpdateUnableTimeSlotDto;
import com.kujproject.kuj.dto.user.UserRespDto;

import java.util.List;

public interface MeetingTimeCalculatorService {
    TimeSetupRespDto initialTimeSetup(InitialTimeSetupReqDto initialTimeSetupReqDto, Long boardId) throws JsonProcessingException;
    TimeSetupRespDto findByTimeSetupId(Long timeSetupId) throws JsonProcessingException;
    void deleteByTimeSetupId(Long timeSetupId);
    UnableTimeSlotEntity updateUnableTimeSlot(UpdateUnableTimeSlotDto updateUnableTimeSlotDto, Long timeSetupId) throws JsonProcessingException;
    MeetingTimeResultRespDto getResultByTimeSetupId(Long timeSetupId) throws JsonProcessingException;
    List<TimeSetupRespDto> findAllTimeSetupByUserId(String userId) throws JsonProcessingException;
    List<UserRespDto> findInvitedUsersByTimeSetupId(Long timeSetupId) throws JsonProcessingException;
}
