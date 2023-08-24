package com.kujproject.kuj.dto.meetingTimeCalculator;

import com.kujproject.kuj.validation.meetingTimeCalculator.ExistUserCheckValidator;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@ExistUserCheckValidator(board = "boardId", users = "users", message = "모시모시")
public class InitialTimeSetupReqDto {
    List<@Valid Days> days;
    List<@Valid Times> times;
    List<@Valid TargetTime> targetTimes;
    List<@Valid User> users;
    Long boardId;
}