package com.kujproject.kuj.domain.board;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardEntity {
    @Id
    Long boardId;
    String title;
    String isPublic;

    // member , list, starred 와 연관관계 선언
}
