package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.board.BoardRespDto;
import com.kujproject.kuj.dto.user.*;

import java.util.List;


public interface UserService {
    UserRespDto signUp(SignUpReqDto signUpReqDto);
    UserRespDto findUserById(String id);
    List<BoardRespDto> findUsersBoard(String id);
    List<UserRespDto> findAllUser();
    UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto);
    UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto);
    void updatePassword(String userId, UpdatePasswordDto updatePasswordDto);
    void deleteUser(String userId);
    boolean existByUserId(String userId);
    boolean existByEmail(String email);
    boolean existByPhoneNum(String phoneNum);
}
