package com.kujproject.kuj.service;

import com.kujproject.kuj.domain.UserVo;
import com.kujproject.kuj.entity.User;
import com.kujproject.kuj.persistent.UserDao;
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
    public User createUser(UserVo userVo) {
        // new 연산 권장?
        User userEntity = new User();

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
        Optional<User> user = userDao.findById(userId);

        if(user.isPresent()) {
            userDao.deleteById(userId);
            return true;
        }

        return false;
    }

    @Override
    public User modifyUserInformation(User user) {
        // validation 및 확인 절차 추후 검토
        return userDao.save(user);
    }

    @Override
    public Optional<User> searchUserById(String userId) {
        return userDao.findById(userId);
    }
}
