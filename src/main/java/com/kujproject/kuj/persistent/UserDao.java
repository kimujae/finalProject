package com.kujproject.kuj.persistent;

import com.kujproject.kuj.entity.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDao extends Repository<User, String> {
    User save(User user);
    Optional<User> findById(String userId);
    Optional<User> findByEmail(String email);
    Optional<User> deleteById(String userId);
    Optional<User> findByUserName(String userName);

}
