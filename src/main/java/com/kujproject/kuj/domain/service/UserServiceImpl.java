package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.dto.board.BoardRespDto;
import com.kujproject.kuj.dto.user.*;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserRespDto findUserById(String userId) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);
        UserEntity user =  userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

       UserRespDto userRespDto = UserRespDto.convertedBy(user).build();
       return userRespDto;
    }


    @Override
    @Secured("ROLE_ADMIN")
    public List<UserRespDto> findAllUser() {
        List<UserEntity> allUsers = userDao.findAll();
        List<UserRespDto> userRespDtoList = new ArrayList<>();

        for(UserEntity user : allUsers) {
            UserRespDto userRespDto = UserRespDto.convertedBy(user).build();
            userRespDtoList.add(userRespDto);
        }
        return userRespDtoList;
    }


    @Override
    @Transactional
    public UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);
        UserEntity user =  userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        user.changeProfile(updateProfileDto);
        userDao.save(user);
        return updateProfileDto;
    }


    @Override
    @Transactional
    public UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);
        UserEntity user =  userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        user.changeEmail(updateEmailDto);
        userDao.save(user);
        return updateEmailDto;
    }


    @Override
    @Transactional
    public void updatePassword(String userId, UpdatePasswordDto updatePasswordDto) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);
        UserEntity user =  userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        updatePasswordDto.setPassword(passwordEncoder.encode(updatePasswordDto.getPassword()));
        user.changePassword(updatePasswordDto);
        userDao.save(user);
    }


    @Override
    @Transactional
    public void deleteUser(String userId) {
        int deletedCount = userDao.deleteByUserId(userId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
        }
    }


    @Override
    public boolean existByUserId(String userId) {
        boolean isExistByUserId = userDao.existsByUserId(userId);
        return isExistByUserId;
    }

    @Override
    public boolean existByEmail(String email) {
        boolean isExistByEmail = userDao.existsByEmail(email);
        return isExistByEmail;
    }

    @Override
    public boolean existByPhoneNum(String phoneNum) {
        boolean isExistByPhoneNum = userDao.existsByPhoneNum(phoneNum);
        return isExistByPhoneNum;
    }


}
