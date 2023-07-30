package com.kujproject.kuj.domain.list;

import com.kujproject.kuj.domain.board.BoardEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListEntity {
    @Id
    Long listId;
    String title;
    int listOrder;

    @ManyToOne
    @JoinColumn(name = "board_id")
    BoardEntity boardEntity;
}
