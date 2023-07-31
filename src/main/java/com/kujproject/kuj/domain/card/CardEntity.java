package com.kujproject.kuj.domain.card;

import com.kujproject.kuj.domain.list.ListEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "list_id")
    ListEntity list;

}
