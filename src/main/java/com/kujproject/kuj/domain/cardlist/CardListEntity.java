package com.kujproject.kuj.domain.cardlist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.dto.cardlist.CreateCardListReqDto;
import com.kujproject.kuj.dto.cardlist.UpdateCardListOrderDto;
import com.kujproject.kuj.dto.cardlist.UpdateCardListTitleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name ="cardlist")
@Builder
public class CardListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardlistId;
    private String title;
    @Column(unique = true)
    private int cardlistOrder;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonManagedReference
    private BoardEntity board;

    public void changeTitle(UpdateCardListTitleDto updateCardListTitleDto) {
        this.title = updateCardListTitleDto.getTitle();
    }

    public void changeListOrder(UpdateCardListOrderDto updateCardListOrderDto) {
        this.cardlistOrder = updateCardListOrderDto.getCardlistOrder();
    }

    public static CardListEntity convertedBy(CreateCardListReqDto createCardListReqDto, BoardEntity board) {
        return CardListEntity.builder()
                .title(createCardListReqDto.getTitle())
                .cardlistOrder(createCardListReqDto.getCardlistOrder())
                .board(board)
                .build();
    }
}
