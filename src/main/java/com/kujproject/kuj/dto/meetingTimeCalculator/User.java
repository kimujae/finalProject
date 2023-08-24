package com.kujproject.kuj.dto.meetingTimeCalculator;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {
    @Size(max = 20, message = UserConstraint.USERID_SIZEMAX_MSG)
    String userId;
}
