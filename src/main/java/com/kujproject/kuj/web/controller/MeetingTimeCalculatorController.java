package com.kujproject.kuj.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kujproject.kuj.domain.meetingTimeCalculator.UnableTimeSlotEntity;
import com.kujproject.kuj.domain.service.MeetingTimeCalculatorService;
import com.kujproject.kuj.dto.meetingTimeCalculator.*;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "MeetingTimeCalculator", description = "MeetingTimeCalculator CRUD API입니다.")
public class MeetingTimeCalculatorController {
    private final MeetingTimeCalculatorService meetingTimeService;


    @Operation(summary = "Timesetup 조회", description = "timeSetupId를 가진 timeSetup 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Timesetup 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/timeSetup/{timeSetupId}")
    public ResponseEntity<ApiResponse> findByTimeSetupId(@PathVariable  Long timeSetupId) throws JsonProcessingException {

        TimeSetupRespDto timeSetupRespDto = meetingTimeService.findByTimeSetupId(timeSetupId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(timeSetupRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);

    }


    @Operation(summary = "보드네 Timesetup 생성", description = "boardId를 가진 board에 timeSetup 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Board에 Timesetup 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/board/{boardId}/timeSetups")
    public ResponseEntity<?> initialTimeSetup(@PathVariable Long boardId, @Valid @RequestBody InitialTimeSetupReqDto initialTimeSetupReqDto) throws JsonProcessingException {

        TimeSetupRespDto timeSetupRespDto = meetingTimeService.initialTimeSetup(initialTimeSetupReqDto, boardId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(timeSetupRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @Operation(summary = "TimeSetup 삭제", description = "timeSetupId를 timeSetup 삭제합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Timesetup 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @DeleteMapping("/timeSetup/{timeSetupId}")
    public ResponseEntity<ApiResponse> deleteTimeSetupByTimeSetupId(@PathVariable Long timeSetupId) {

        meetingTimeService.deleteByTimeSetupId(timeSetupId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }


    @Operation(summary = "UnableTimeSlot 생성", description = "timeSetupId를 가진 UnableTimeSlot을 생성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "UnableTimeSlot 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @PostMapping("/unableTimeSlot/{timeSetupId}")
    public ResponseEntity updateUnableTimeSlot(@PathVariable Long timeSetupId, @Valid @RequestBody UpdateUnableTimeSlotDto updateUnableTimeSlotDto) throws JsonProcessingException {

        UnableTimeSlotEntity unableTimeSlotEntity = meetingTimeService.updateUnableTimeSlot(updateUnableTimeSlotDto, timeSetupId);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(unableTimeSlotEntity)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.CREATED);
    }


    @Operation(summary = "meetingTimeCalculator의 result 조회", description = "timeSetupId를 가진 result를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "result 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/meetingTimeCalculator/result/{timeSetupId}")
    public ResponseEntity getResultByTimeSetupId(@PathVariable Long id) throws JsonProcessingException {

        MeetingTimeResultRespDto meetingTimeResultRespDto = meetingTimeService.getResultByTimeSetupId(id);
        return new ResponseEntity(ApiResponse.builder()
                .result(meetingTimeResultRespDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "User의 모든 timeSetups 조회", description = "userId를 가진 user가 참여하는 모든 timeSetup을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "user의 모든 timeSetup 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/user/{userId}/timeSetups")
    public ResponseEntity findAllTimeSetupsByUserId(@PathVariable String userId) throws JsonProcessingException {

        List<TimeSetupRespDto> timeSetupRespDtoList = meetingTimeService.findAllTimeSetupByUserId(userId);
        return new ResponseEntity(ApiResponse.builder()
                .result(timeSetupRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @Operation(summary = "TimeSetup에 초대된 모든 유저 조회", description = "timeSetupId를 가진 timeSetup의 초대된 모든 user를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "timeSetup의 초대된 모든 유저 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류 발생")
    })
    @GetMapping("/timeSetup/{timeSetupId}/user")
    public ResponseEntity findInvitedUsersByTimeSetupId(@PathVariable Long timeSetupId) throws JsonProcessingException {

        List<UserRespDto> userRespDtoList = meetingTimeService.findInvitedUsersByTimeSetupId(timeSetupId);
        return new ResponseEntity(ApiResponse.builder()
                .result(userRespDtoList)

                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }
}
