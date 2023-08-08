package com.kujproject.kuj.dto.user.constraint;

import lombok.Getter;

@Getter
public class UserConstraint {

    public static final String USERID_NOTEMPTY_MSG =
            "ID는 필수 항목입니다.";

    public static final String USERID_SIZEMAX_MSG =
            "ID는 20자를 초과할 수 없습니다.";




    public static final String PWD_NOTEMPTY_MSG =
            "비밀번호는 필수 항목입니다.";

    public static final String PWD_SIZEMIN_MSG =
            "비밀번호는 8자리 이상으로 설정해야 합니다.";

    public static final String PWD_SIZEMAX_MSG =
            "비밀번호는 15자리를 초과할 수 없습니다.";

    public static final String PWD_REGEXP =
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";




    public static final String USERNAME_NOTEMPTY_MSG =
            "이름은 필수 항목입니다.";

    public static final String USERNAME_SIZEMAX_MSG =
            "이름은 20자를 초과할 수 없습니다.";





    public static final String CHECKPWD_NOTEMPTY_MSG =
            "비밀번호확인은 필수 항목입니다.";

    public static final String PWD_PATTERN_MSG =
            "비밀번호는 최소 1개의 숫자와 최소 1개의 문자를 포함해야합니다.";




    public static final String EMAIL =
            "유효한 이메일을 입력해주세요.";

    public static final String EMAIL_SIZEMAX_MSG =
            "이메일은 100자를 초과할 수 없습니다.";





    public static final String PHONENUM_NOTEMPTY_MSG =
            "전화번호는 필수 항목입니다.";

    public static final String PHONENUM_REGEXP =
            "\\d{3}-?\\d{4}-?\\d{4}";

    public static final String PHONENUM_PATTERN_MSG =
            "올바른 전화번호를 입력해주세요.";

    public static final String PHONENUM_SIZEMAX_MSG =
            "전화번호는 20자를 초과할 수 없습니다.";
}
