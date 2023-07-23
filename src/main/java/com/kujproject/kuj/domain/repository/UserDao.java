package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.user.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDao extends Repository<UserEntity, String> {
    UserEntity save(UserEntity userEntity);
    Optional<UserEntity> findById(String userId);
    Optional<UserEntity> existsByEmail(String email);
    Optional<UserEntity> existsByUserId(String userId);
    Optional<UserEntity> deleteById(String userId);
    Optional<UserEntity> findByUserName(String userName);

}