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
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
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
        CommentEntity comment = commentEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.COMMENT_NOT_FOUND));


        return CommentRespDto.convertedBy(comment, comment.getUser().getUserId());

    }

    @Override
    public CommentRespDto createComment(CreateCommentReqDto createCommentReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

         Optional<UserEntity> userEntity = userDao.findByUserId(createCommentReqDto.getUserId());
         UserEntity user = userEntity.orElseThrow(() ->
                 new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        CommentEntity comment = CommentEntity.convertedBy(createCommentReqDto, card, user);
        commentDao.save(comment);

        return CommentRespDto.convertedBy(comment, user.getUserId());
    }

    @Override
    public UpdateContentReqDto updateContent(UpdateContentReqDto updateContentReqDto, Long commentId) {
        Optional<CommentEntity> commentEntity = commentDao.findByCommentId(commentId);
        CommentEntity comment = commentEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.COMMENT_NOT_FOUND));

        comment.changeContent(updateContentReqDto);
        commentDao.save(comment);

        return updateContentReqDto;
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId) {
        int deletedCount = commentDao.deleteByCommentId(commentId);
        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.COMMENT_NOT_FOUND);
        }
    }
}
