package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.user.UserVo;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.SignUpReqDto;
import com.kujproject.kuj.dto.UpdateEmailReqDto;
import com.kujproject.kuj.dto.UpdatePasswordReqDto;
import com.kujproject.kuj.dto.UpdateProfileReqDto;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserEntity signUp(SignUpReqDto signUpReqDto);

    public Optional<UserEntity> findUserById(String id);

    public Optional<List<UserEntity>> findAllUser();

    UpdateProfileReqDto updateUserProfile(String userId, UpdateProfileReqDto updateProfileReqDto);

    UpdateEmailReqDto updateEmail(String userId, UpdateEmailReqDto updateEmailReqDto);

    boolean updatePassword(String userId, UpdatePasswordReqDto updatePasswordReqDto);

    public boolean deleteUser(String userId);

    public boolean existByUserId(String userId);

    public boolean existByEmail(String email);
}
