package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.comment.CommentEntity;
import com.kujproject.kuj.domain.repository.CardDao;
import com.kujproject.kuj.domain.repository.CommentDao;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private final CommentDao commentDao;
    private final CardDao cardDao;
    private final UserDao userDao;

    public CommentServiceImpl(CommentDao commentDao, CardDao cardDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.cardDao = cardDao;
        this.userDao = userDao;
    }

    @Override
    public CommentRespDto findCommentById(Long commentId) {
        Optional<CommentEntity> commentEntity = commentDao.findByCommentId(commentId);

        if(commentEntity.isPresent()) {
            CommentRespDto commentRespDto = new CommentRespDto();
            CommentEntity comment = commentEntity.get();

            commentRespDto.setContent(comment.getContent());
            commentRespDto.setUserId(comment.getUser().getUserId());

            return commentRespDto;
        }
        return null;
    }

    @Override
    public CreateCommentReqDto createComment(CreateCommentReqDto createCommentReqDto, Long cardId) {
        CommentEntity commentEntity = new CommentEntity();
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            Optional<UserEntity> userEntity = userDao.findById(createCommentReqDto.getUserId());
            if(userEntity.isPresent()) {
                UserEntity user = userEntity.get();
                CardEntity card = cardEntity.get();

                commentEntity.setContent(createCommentReqDto.getContent());
                commentEntity.setCard(card);
                commentEntity.setUser(user);

                commentDao.save(commentEntity);
                return createCommentReqDto;
            }
            return null;
        }
        return null;
    }

    @Override
    public UpdateContentReqDto updateContent(UpdateContentReqDto updateContentReqDto, Long commentId) {
        Optional<CommentEntity> commentEntity = commentDao.findByCommentId(commentId);

        if(commentEntity.isPresent()) {
            CommentEntity comment = commentEntity.get();

            comment.setContent(updateContentReqDto.getContent());
            commentDao.save(comment);

            return updateContentReqDto;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteComment(Long commentId) {
        int deletedCommentCount = commentDao.deleteByCommentId(commentId);
        return true;
    }
}
