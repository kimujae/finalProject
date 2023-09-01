package com.kujproject.kuj.dto.meetingTimeCalculator;

import com.kujproject.kuj.validation.meetingTimeCalculator.ExistUserCheckValidator;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@ExistUserCheckValidator(board = "boardId", users = "users")
public class InitialTimeSetupReqDto {
    private List<@Valid Days> days;
    private List<@Valid Times> times;
    private List<@Valid TargetTime> targetTimes;
    private List<@Valid User> users;
    private Long boardId;
}