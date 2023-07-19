package com.kujproject.kuj.service;

import com.kujproject.kuj.entity.User;
import com.kujproject.kuj.persistent.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {
    public void createUser(User user);
    public boolean login();
    public boolean deleteUser();
    // public boolean modifyUserInfo(User user);
}
