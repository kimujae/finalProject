package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateProfileDto {
    @NotEmpty(message = UserConstraint.USERNAME_NOTEMPTY_MSG)
    @Size(max = 20, message = UserConstraint.USERNAME_SIZEMAX_MSG)
    private String userName;
}
