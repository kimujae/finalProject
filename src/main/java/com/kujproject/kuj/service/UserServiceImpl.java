package com.kujproject.kuj.service;

import com.kujproject.kuj.domain.user.UserVo;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public UserEntity createUser(UserVo userVo) {
        // new 연산 권장?
        UserEntity userEntity = new UserEntity();

        userEntity.setUserId(userVo.getUserId());
        userEntity.setPassword(passwordEncoder.encode(userVo.getPassword()));
        userEntity.setEmail(userVo.getEmail());
        userEntity.setUserName(userVo.getUserName());
        userEntity.setPhone_num(userVo.getPhoneNum());
        userEntity.setRole("ROLE_USER");

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
    public UserEntity modifyUserInformation(UserEntity userEntity) {
        // validation 및 확인 절차 추후 검토
        return userDao.save(userEntity);
    }

    @Override
    public Optional<UserEntity> searchUserById(String userId) {
        return userDao.findById(userId);
    }
}
