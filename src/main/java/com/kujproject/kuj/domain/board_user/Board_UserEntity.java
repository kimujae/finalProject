package com.kujproject.kuj.domain.board_user;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board_UserEntity {
    @EmbeddedId
    CompositeKey board_user;
    String boardName;
    String userName;
}
