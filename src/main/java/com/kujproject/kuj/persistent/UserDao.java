package com.kujproject.kuj.persistent;

import com.kujproject.kuj.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserDao extends Repository {
    void save(User user);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
}
