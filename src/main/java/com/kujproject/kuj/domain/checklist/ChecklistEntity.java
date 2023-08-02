package com.kujproject.kuj.domain.checklist;

import com.kujproject.kuj.domain.card.CardEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "checklist")
public class ChecklistEntity {
    @Id
    Long checklistId;
    String title;
    int progress;

    @ManyToOne
    @JoinColumn(name = "card_id")
    CardEntity card;
}
