package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.comment.CommentEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CommentDao extends Repository<CommentEntity, String> {
    /*
        CRUD
     */
    CommentEntity save(CommentEntity commentEntity);
    int deleteByCommentId(Long commentId);
    Optional<CommentEntity> findByCommentId(Long commentId);
}
