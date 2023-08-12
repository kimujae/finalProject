package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.comment.CommentRespDto;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;

public interface CommentService {
    CommentRespDto findCommentById(Long commentId);
    CommentRespDto createComment(CreateCommentReqDto createCommentReqDto, Long cardId);
    UpdateContentReqDto updateContent(UpdateContentReqDto updateContentReqDto, Long commentId);
    void deleteComment(Long commentId);
}
