package com.kujproject.kuj.domain.card;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.comment.CommentEntity;
import com.kujproject.kuj.dto.card.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;
    private String title;
    @Lob
    private String description;
    private String label;
    private String cover;
    @Column(unique = true)
    private int cardOrder;
    private String storedFileName;
    private String uploadFileName;
    private LocalDate startdate;
    private LocalDate duedate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardlist_id")
    @JsonManagedReference
    private CardListEntity cardlist;

    @OneToMany (mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<ChecklistEntity> checklist;


    @OneToMany (mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CommentEntity> comment;

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

    public void changeList(CardListEntity cardlist) {
        this.cardlist = cardlist;
    }
    public void changeUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void changeStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public static CardEntity convertedBy(CreateCardReqDto createCardReqDto, CardListEntity cardlist) {
        return CardEntity.builder()
                .title(createCardReqDto.getTitle())
                .cardlist(cardlist)
                .build();
    }
}
