package com.kujproject.kuj.domain.list;

import com.kujproject.kuj.domain.board.BoardEntity;
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
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long listId;
    String title;
    int listOrder;

    @ManyToOne
    @JoinColumn(name = "board_id")
    BoardEntity board;
}
