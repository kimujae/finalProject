package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.MeetingTimeService;
import com.kujproject.kuj.dto.meeting_time.CreateMeetingTimeReqDto;
import com.kujproject.kuj.dto.meeting_time.MeetingTimeRespDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MeetingTimeController {
    private final MeetingTimeService meetingTimeService;

    public MeetingTimeController(MeetingTimeService meetingTimeService) {
        this.meetingTimeService = meetingTimeService;
    }


    @GetMapping("/meetingTime/{id}")
    public ResponseEntity<ApiResponse> findByMeetingTimeId(@PathVariable  Long id) {
        MeetingTimeRespDto meetingTimeRespDto = meetingTimeService.findByMeetingId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(meetingTimeRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);

    }


    @PostMapping("/meetingTime")
    public ResponseEntity<ApiResponse> createMeetingTime(@Valid @RequestBody CreateMeetingTimeReqDto createMeetingTimeReqDto) {
        MeetingTimeRespDto meetingTimeRespDto = meetingTimeService.createMeetingTime(createMeetingTimeReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(meetingTimeRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @DeleteMapping("meetingTime/{id}")
    public ResponseEntity<ApiResponse> deleteMeetingTime(@PathVariable Long id) {
        meetingTimeService.deleteByMeetingId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
