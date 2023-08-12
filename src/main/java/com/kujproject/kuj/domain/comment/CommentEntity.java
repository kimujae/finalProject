package com.kujproject.kuj.domain.comment;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.comment.CreateCommentReqDto;
import com.kujproject.kuj.dto.comment.UpdateContentReqDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(builderMethodName = "builder")
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long commentId;
    String content;

    @ManyToOne
    @JoinColumn(name = "card_id")
    CardEntity card;

    @ManyToOne
    @JoinColumn(name = "user_id")
    UserEntity user;


    public void changeContent(UpdateContentReqDto updateContentReqDto) {
        this.content = updateContentReqDto.getContent();
    }

    public static CommentEntity convertedBy(CreateCommentReqDto createCommentReqDto, CardEntity card, UserEntity user) {
        return CommentEntity.builder()
                .content(createCommentReqDto.getContent())
                .card(card)
                .user(user)
                .build();
    }
}
