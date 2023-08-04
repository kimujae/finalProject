package com.kujproject.kuj.domain.comment;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
}
