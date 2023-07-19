package com.kujproject.kuj.service;

import com.kujproject.kuj.entity.User;

import java.util.Optional;

public interface UserService {
    public User createUser(User user);


    public boolean deleteUser(String userId);

    public User modifyUserInformation(User user);
    public Optional<User> searchUserById(String id);
}
