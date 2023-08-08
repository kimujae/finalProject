package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UpdateProfileDto {
    @NotEmpty(message = UserConstraint.USERNAME_NOTEMPTY_MSG)
    @Size(max = 20, message = UserConstraint.USERNAME_SIZEMAX_MSG)
    private String userName;
}
