package com.kujproject.kuj.service;

import com.kujproject.kuj.domain.UserVo;
import com.kujproject.kuj.entity.User;
import com.kujproject.kuj.persistent.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserVo userVo;
    @Override
    public User createUser(User user) {
        // passWord 암호화
        return userDao.save(user);
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
