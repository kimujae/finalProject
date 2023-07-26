package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.user.*;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserEntity signUp(SignUpReqDto signUpReqDto);

    public Optional<UserRespDto> findUserById(String id);

    public Optional<List<UserEntity>> findAllUser();

    UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto);

    UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto);

    boolean updatePassword(String userId, UpdatePasswordDto updatePasswordDto);

    public boolean deleteUser(String userId);

    public boolean existByUserId(String userId);

    public boolean existByEmail(String email);
}
