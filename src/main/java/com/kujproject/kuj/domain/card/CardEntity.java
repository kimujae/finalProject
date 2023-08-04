package com.kujproject.kuj.domain.card;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.comment.CommentEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    int cardOrder;
    String attachmentPath;
    LocalDate startdate;
    LocalDate duedate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "list_id")
    @JsonManagedReference
    ListEntity list;

    @OneToMany (mappedBy = "card", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<ChecklistEntity> checklist;


    @OneToMany (mappedBy = "card", fetch = FetchType.LAZY)
    @JsonManagedReference
    List<CommentEntity> comment;
}
