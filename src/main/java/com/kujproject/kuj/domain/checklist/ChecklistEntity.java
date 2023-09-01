package com.kujproject.kuj.domain.checklist;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistProgressDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistTitleDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "checklist")
public class ChecklistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checklistId;
    private String title;
    private int progress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @JsonManagedReference
    private CardEntity card;

    @OneToMany(mappedBy = "checklist", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<TodoCheckEntity> check;

    public void changeTitle(UpdateChecklistTitleDto updateChecklistTitleDto) {
        this.title = updateChecklistTitleDto.getTitle();
    }

    public void changeProgress(UpdateChecklistProgressDto updateChecklistProgressDto) {
        this.progress = updateChecklistProgressDto.getProgress();
    }
    public static ChecklistEntity convertedBy(CreateChecklistReqDto createChecklistReqDto, CardEntity card) {
        return ChecklistEntity.builder()
                .title(createChecklistReqDto.getTitle())
                .card(card)
                .build();
    }
}
