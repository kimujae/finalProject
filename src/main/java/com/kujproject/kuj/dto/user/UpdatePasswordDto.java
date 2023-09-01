package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import com.kujproject.kuj.validation.user.EqualsFieldValidator;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsFieldValidator(field1 = "password", field2 = "checkPassword", message = "비밀번호가 일치하지 않습니다.")
public class UpdatePasswordDto {
    @NotEmpty(message = UserConstraint.PWD_NOTEMPTY_MSG)
    @Size(min = 8 , message = UserConstraint.PWD_SIZEMIN_MSG)
    @Size(max = 15, message = UserConstraint.PWD_SIZEMAX_MSG)
    @Pattern(regexp = UserConstraint.PWD_REGEXP, message = UserConstraint.PWD_PATTERN_MSG)
    private String password;

    @NotEmpty(message = UserConstraint.CHECKPWD_NOTEMPTY_MSG)
    private String checkPassword;
}
