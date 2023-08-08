package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.user.*;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserRespDto signUp(SignUpReqDto signUpReqDto);
    UserRespDto findUserById(String id) throws EntityNotFoundException;
    List<BoardEntity>findUsersBoard(String id) throws EntityNotFoundException;
    List<UserRespDto> findAllUser();
    UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto) throws EntityNotFoundException;
    UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto) throws EntityNotFoundException;
    void updatePassword(String userId, UpdatePasswordDto updatePasswordDto) throws EntityNotFoundException;
    void deleteUser(String userId) throws EntityNotFoundException;
    boolean existByUserId(String userId);
    boolean existByEmail(String email);
    boolean existByPhoneNum(String phoneNum);
}
