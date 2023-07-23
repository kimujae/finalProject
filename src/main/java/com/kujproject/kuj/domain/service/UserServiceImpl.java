package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.user.UserVo;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.dto.SignUpReqDto;
import com.kujproject.kuj.dto.UpdateEmailReqDto;
import com.kujproject.kuj.dto.UpdatePasswordReqDto;
import com.kujproject.kuj.dto.UpdateProfileReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    //Dto로 해결
    @Override
    public UserEntity signUp(SignUpReqDto signUpReqDto) {
        // new 연산 권장?
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(signUpReqDto.getUserId());
        userEntity.setPassword(passwordEncoder.encode(signUpReqDto.getPassword()));
        userEntity.setEmail(signUpReqDto.getEmail());
        userEntity.setUserName(signUpReqDto.getUserName());
        userEntity.setPhone_num(signUpReqDto.getPhoneNum());
        userEntity.setRole("ROLE_USER");

        return userDao.save(userEntity);
    }


    @Override
    public Optional<UserEntity> findUserById(String userId) {
        return userDao.findById(userId);
    }

    @Override
    public Optional<List<UserEntity>> findAllUser() {
        return Optional.empty();
    }

    @Override
    public UserEntity updateUserProfile(String userId, UpdateProfileReqDto updateProfileReqDto) {
        UserEntity userEntity = userDao.findById(userId).get();
        userEntity.setUserName(updateProfileReqDto.getUserName());
        return userDao.save(userEntity);
    }

    @Override
    public UserEntity updateEmail(String userId, UpdateEmailReqDto updateEmailReqDto) {
        UserEntity userEntity = userDao.findById(userId).get();
        userEntity.setEmail(updateEmailReqDto.getEmail());
        return userDao.save(userEntity);
    }

    @Override
    public UserEntity updatePassword(String userId, UpdatePasswordReqDto updatePasswordReqDto) {
        UserEntity userEntity = userDao.findById(userId).get();
        userEntity.setEmail(updatePasswordReqDto.getPassword());
        return userDao.save(userEntity);
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
