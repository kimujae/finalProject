package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.user.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Repository<UserEntity, String> {
    UserEntity save(UserEntity userEntity);
    //@Query("SELECT DISTINCT u FROM user u LEFT JOIN FETCH u.boards b WHERE u.userId = :userId")
    Optional<UserEntity> findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
    boolean existsByPhoneNum(String phoneNum);

    int deleteByUserId(String userId);
    List<UserEntity> findAll();

}
