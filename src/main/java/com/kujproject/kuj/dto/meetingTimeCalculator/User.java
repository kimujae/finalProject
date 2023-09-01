package com.kujproject.kuj.dto.meetingTimeCalculator;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    @Size(max = 20, message = UserConstraint.USERID_SIZEMAX_MSG)
    private String userId;
}
