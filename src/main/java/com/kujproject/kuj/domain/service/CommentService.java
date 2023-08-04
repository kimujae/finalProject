package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;

public interface CommentService {
    CommentRespDto findCommentById(Long commentId);
    CreateCommentReqDto createComment(CreateCommentReqDto createCommentReqDto, Long cardId);
    UpdateContentReqDto updateContent(UpdateContentReqDto updateContentReqDto, Long commentId);
    boolean deleteComment(Long commentId);
}
