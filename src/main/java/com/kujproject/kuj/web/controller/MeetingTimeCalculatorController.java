package com.kujproject.kuj.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kujproject.kuj.domain.meetingTimeCalculator.UnableTimeSlotEntity;
import com.kujproject.kuj.domain.service.MeetingTimeCalculatorService;
import com.kujproject.kuj.dto.meetingTimeCalculator.*;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MeetingTimeCalculatorController {
    private final MeetingTimeCalculatorService meetingTimeService;

    public MeetingTimeCalculatorController(MeetingTimeCalculatorService meetingTimeService) {
        this.meetingTimeService = meetingTimeService;
    }


    @GetMapping("/timeSetup/{id}")
    public ResponseEntity<ApiResponse> findByTimeSetupId(@PathVariable  Long id) throws JsonProcessingException {

        TimeSetupRespDto timeSetupRespDto = meetingTimeService.findByTimeSetupId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(timeSetupRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);

    }


    @PostMapping("/board/{id}/timeSetup")
    public ResponseEntity<?> initialTimeSetup(@PathVariable Long id, @Valid @RequestBody InitialTimeSetupReqDto initialTimeSetupReqDto) throws JsonProcessingException {

        TimeSetupRespDto timeSetupRespDto = meetingTimeService.initialTimeSetup(initialTimeSetupReqDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(timeSetupRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @DeleteMapping("/timeSetup/{id}")
    public ResponseEntity<ApiResponse> deleteTimeSetupByTimeSetupId(@PathVariable Long id) {

        meetingTimeService.deleteByTimeSetupId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/unableTimeSlot/{id}")
    public ResponseEntity updateUnableTimeSlot(@PathVariable Long id, @Valid @RequestBody UpdateUnableTimeSlotDto updateUnableTimeSlotDto) throws JsonProcessingException {

        UnableTimeSlotEntity unableTimeSlotEntity = meetingTimeService.updateUnableTimeSlot(updateUnableTimeSlotDto, id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(unableTimeSlotEntity)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/meetingTimeCalculator/result/{id}")
    public ResponseEntity getResultByTimeSetupId(@PathVariable Long id) throws JsonProcessingException {

        MeetingTimeResultRespDto meetingTimeResultRespDto = meetingTimeService.getResultByTimeSetupId(id);
        return new ResponseEntity(ApiResponse.builder()
                .result(meetingTimeResultRespDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/timeSetup")
    public ResponseEntity findAllTimeSetupByUserId(@PathVariable String id) throws JsonProcessingException {

        List<TimeSetupRespDto> timeSetupRespDtoList = meetingTimeService.findAllTimeSetupByUserId(id);
        return new ResponseEntity(ApiResponse.builder()
                .result(timeSetupRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/timeSetup/{id}/user")
    public ResponseEntity findInvitedUsersByTimeSetupId(@PathVariable Long id) throws JsonProcessingException {

        List<UserRespDto> userRespDtoList = meetingTimeService.findInvitedUsersByTimeSetupId(id);
        return new ResponseEntity(ApiResponse.builder()
                .result(userRespDtoList)

                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }
}
