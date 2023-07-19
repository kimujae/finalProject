package com.kujproject.kuj.service;

import com.kujproject.kuj.entity.User;

public interface UserService {
    public void createUser(User user);
    public boolean login();
    public boolean deleteUser();
    // public boolean modifyUserInfo(User user);
}
