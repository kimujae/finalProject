package com.kujproject.kuj.domain.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = " board")
@NoArgsConstructor
@Getter
@Setter
public class CustomBoardEntity {
    @Id
    Long boardId;
    String boardName;
}
