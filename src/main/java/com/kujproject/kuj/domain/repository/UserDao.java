package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.user.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserDao extends Repository<UserEntity, String> {
    UserEntity save(UserEntity userEntity);
    @Query("SELECT DISTINCT u FROM user u LEFT JOIN FETCH u.boards b WHERE u.userId = :userId")
    Optional<UserEntity> findById(String userId);
    Optional<UserEntity> existsByEmail(String email);
    Optional<UserEntity> existsByUserId(String userId);
    Optional<UserEntity> deleteById(String userId);
    Optional<UserEntity> findByUserName(String userName);

}
