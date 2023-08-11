package com.kujproject.kuj.domain.card;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.comment.CommentEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.dto.board.CreateBoardReqDto;
import com.kujproject.kuj.dto.card.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(builderMethodName = "builder")
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cardId;
    String title;
    @Lob
    String description;
    String label;
    String cover;
    @Column(unique = true)
    int cardOrder;
    String storedFileName;
    String uploadFileName;
    LocalDate startdate;
    LocalDate duedate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    @JsonManagedReference
    ListEntity list;

    @OneToMany (mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<ChecklistEntity> checklist;


    @OneToMany (mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<CommentEntity> comment;

    public void changeTitle(UpdateCardTitleDto updateCardTitleDto) {
        this.title = updateCardTitleDto.getTitle();
    }

    public void changeDescription(UpdateCardDescDto updateCardDescDto) {
        this.description = updateCardDescDto.getDescription();
    }

    public void changeLabel(UpdateCardLabelDto updateCardLabelDto) {
        this.label = updateCardLabelDto.getLabel();
    }

    public void changeCover(UpdateCardCoverDto updateCardCoverDto) {
        this.cover = updateCardCoverDto.getCover();
    }

    public void changeCardOrder(UpdateCardOrderDto updateCardOrderDto) {
        this.cardOrder = updateCardOrderDto.getCardOrder();
    }

    public void changeDate(UpdateCardDateDto updateCardDateDto) {
        this.duedate = updateCardDateDto.getDuedate();
        this.startdate = updateCardDateDto.getStartdate();
    }

    public void changeList(ListEntity list) {
        this.list = list;
    }
    public void changeUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void changeStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public static CardEntity convertedBy(CreateCardReqDto createCardReqDto, ListEntity list) {
        return CardEntity.builder()
                .title(createCardReqDto.getTitle())
                .list(list)
                .build();
    }
}
