package com.kujproject.kuj.domain.checklist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "checklist")
public class ChecklistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long checklistId;
    String title;
    int progress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @JsonManagedReference
    CardEntity card;

    @OneToMany(mappedBy = "checklist", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    List<TodoCheckEntity> check;
}
