package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.user.*;

import java.util.List;
import java.util.Optional;


public interface UserService {
    UserEntity signUp(SignUpReqDto signUpReqDto);

    Optional<UserRespDto> findUserById(String id);

    List<BoardEntity>findUsersBoard(String id);
    Optional<List<UserEntity>> findAllUser();

    UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto);

    UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto);

    boolean updatePassword(String userId, UpdatePasswordDto updatePasswordDto);

    boolean deleteUser(String userId);

    boolean existByUserId(String userId);

    boolean existByEmail(String email);
}
