package com.kujproject.kuj.domain.list;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.UpdateListOrderReqDto;
import com.kujproject.kuj.dto.list.UpdateListTitleReqDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name ="list")
@Builder(builderMethodName = "builder")
public class ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long listId;
    String title;
    @Column(unique = true)
    int listOrder;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    BoardEntity board;

    public void changeTitle(UpdateListTitleReqDto updateListTitleReqDto) {
        this.title = updateListTitleReqDto.getTitle();
    }

    public void changeListOrder(UpdateListOrderReqDto updateListOrderReqDto) {
        this.listOrder = updateListOrderReqDto.getListOrder();
    }

    public static ListEntity convertedBy(CreateListReqDto createListReqDto, BoardEntity board) {
        return ListEntity.builder()
                .title(createListReqDto.getTitle())
                .listOrder(createListReqDto.getListOrder())
                .board(board)
                .build();
    }
}
