package com.kujproject.kuj.repository;

import com.kujproject.kuj.domain.user.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDao extends Repository<UserEntity, String> {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findById(String userId);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> deleteById(String userId);
    Optional<UserEntity> findByUserName(String userName);

}
