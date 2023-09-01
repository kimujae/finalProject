package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import com.kujproject.kuj.validation.user.PhoneNumDupCheckValidator;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
public class UpdatePhoneNumDto {
    @Column(unique = true)
    @NotEmpty(message = UserConstraint.PHONENUM_NOTEMPTY_MSG)
    @Pattern(regexp = UserConstraint.PHONENUM_REGEXP, message = UserConstraint.PHONENUM_NOTEMPTY_MSG)
    @Size(max = 20, message = UserConstraint.PHONENUM_SIZEMAX_MSG)
    @PhoneNumDupCheckValidator
    private String phoneNum;
}
