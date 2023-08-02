package com.kujproject.kuj.domain.checklist;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @OneToMany(mappedBy = "checklist")
    List<TodoCheckEntity> check;
}
