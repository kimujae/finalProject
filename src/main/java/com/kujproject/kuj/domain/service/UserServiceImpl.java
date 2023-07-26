package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.dto.user.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    //Dto로 해결
    @Override
    public UserEntity signUp(SignUpReqDto signUpReqDto) {
        // new 연산 권장?
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(signUpReqDto.getUserId());
        userEntity.setPassword(passwordEncoder.encode(signUpReqDto.getPassword()));
        userEntity.setEmail(signUpReqDto.getEmail());
        userEntity.setUserName(signUpReqDto.getUserName());
        userEntity.setPhoneNum(signUpReqDto.getPhoneNum());
        userEntity.setRole("ROLE_USER");

        return userDao.save(userEntity);
    }


    @Override
    public Optional<UserRespDto> findUserById(String userId) {
        Optional<UserEntity> userEntity = userDao.findById(userId);

        if(userEntity.isPresent()) {
           UserRespDto userRespDto = new UserRespDto();

           UserEntity user = userEntity.get();
           userRespDto.setUserId(user.getUserId());
           userRespDto.setEmail(user.getEmail());
           userRespDto.setUserName(user.getUserName());
           userRespDto.setPhoneNum(user.getPhoneNum());

           return Optional.of(userRespDto);
        }

        return null;
    }

    @Override
    public Optional<List<UserEntity>> findAllUser() {
        return Optional.empty();
    }

    @Override
    public UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto) {
        UserEntity userEntity = userDao.findById(userId).get();
        userEntity.setUserName(updateProfileDto.getUserName());

        userDao.save(userEntity);
        return updateProfileDto;
    }

    @Override
    public UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto) {
        UserEntity userEntity = userDao.findById(userId).get();
        userEntity.setEmail(updateEmailDto.getEmail());

        userDao.save(userEntity);
        return updateEmailDto;
    }

    @Override
    public boolean updatePassword(String userId, UpdatePasswordDto updatePasswordDto) {
        UserEntity userEntity = userDao.findById(userId).get();
        userEntity.setEmail(updatePasswordDto.getPassword());

        userDao.save(userEntity);
        return true;
    }

    @Override
    public boolean deleteUser(String userId) {
        Optional<UserEntity> user = userDao.findById(userId);

        if(user.isPresent()) {
            userDao.deleteById(userId);
            return true;
        }

        return false;
    }


    @Override
    public boolean existByUserId(String userId) {
        boolean isExistByUserId = false;
        if(userDao.existsByUserId(userId) != null) {
            isExistByUserId = true;
        }
        return isExistByUserId;
    }

    @Override
    public boolean existByEmail(String email) {
        boolean isExistByEmail = false;
        if(userDao.existsByUserId(email) != null) {
            isExistByEmail = true;
        }
        return isExistByEmail;
    }
}
