package com.kujproject.kuj.dto.user;

import com.kujproject.kuj.dto.user.constraint.UserConstraint;
import com.kujproject.kuj.validation.user.EmailDupCheckValidator;
import com.kujproject.kuj.validation.user.EqualsFieldValidator;
import com.kujproject.kuj.validation.user.IdDupCheckValidator;
import com.kujproject.kuj.validation.user.PhoneNumDupCheckValidator;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;


@Getter
@EqualsFieldValidator(field1 = "password", field2 = "checkPassword", message = "비밀번호가 일치하지 않습니다.")
public class SignUpReqDto {
    @Column(unique = true)
    @NotEmpty(message = UserConstraint.USERID_NOTEMPTY_MSG)
    @Size(max = 20, message = UserConstraint.USERID_SIZEMAX_MSG)
    @IdDupCheckValidator
    private String userId;

    @NotEmpty(message = UserConstraint.PWD_NOTEMPTY_MSG)
    @Size(min = 8 , message = UserConstraint.PWD_SIZEMIN_MSG)
    @Size(max = 15, message = UserConstraint.PWD_SIZEMAX_MSG)
    @Pattern(regexp = UserConstraint.PWD_REGEXP, message = UserConstraint.PWD_PATTERN_MSG)
    private String password;

    @NotEmpty(message = UserConstraint.CHECKPWD_NOTEMPTY_MSG)
    private String checkPassword;

    @NotEmpty(message = UserConstraint.USERNAME_NOTEMPTY_MSG)
    @Size(max = 20, message = UserConstraint.USERNAME_SIZEMAX_MSG)
    private String userName;

    @Column(unique = true)
    @Email(message = UserConstraint.EMAIL)
    @Size(max = 100, message = UserConstraint.EMAIL_SIZEMAX_MSG)
    @EmailDupCheckValidator
    private String email;

    @Column(unique = true)
    @NotEmpty(message = UserConstraint.PHONENUM_NOTEMPTY_MSG)
    @Pattern(regexp = UserConstraint.PHONENUM_REGEXP, message = UserConstraint.PHONENUM_NOTEMPTY_MSG)
    @Size(max = 20, message = UserConstraint.PHONENUM_SIZEMAX_MSG)
    @PhoneNumDupCheckValidator
    private String phoneNum;



    @Builder
    public SignUpReqDto(String userId, String password, String checkPassword, String userName, String email, String phoneNum) {
        this.userId = userId;
        this.password = password;
        this.checkPassword = checkPassword;
        this.userName = userName;
        this.email = email;
        this.phoneNum = phoneNum;
    }


    public void encodingPasswordBy(String encodedPassword) {
        this.password = encodedPassword;
    }
}
