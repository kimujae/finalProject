package com.kujproject.kuj.domain.todo_check;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.todo_check.CreateCheckReqDto;
import com.kujproject.kuj.dto.todo_check.UpdateCompletedReqDto;
import com.kujproject.kuj.dto.todo_check.UpdateDateReqDto;
import com.kujproject.kuj.dto.todo_check.UpdateTitleReqDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(builderMethodName = "builder")
@Table(name ="todo_check")
public class TodoCheckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long checkId;
    String title;
    LocalDate duedate;
    boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "checklist_id")
    @JsonManagedReference
    ChecklistEntity checklist;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    UserEntity user;

    public void changeTitle(UpdateTitleReqDto updateTitleReqDto) {
        this.title = updateTitleReqDto.getTitle();
    }

    public void changeDate(UpdateDateReqDto updateDateReqDto) {
        this.duedate = updateDateReqDto.getDuedate();
    }

    public void changeCompleted(UpdateCompletedReqDto updateCompletedReqDto) {
        this.isCompleted = updateCompletedReqDto.isCompleted();
    }

    public static TodoCheckEntity convertedBy(CreateCheckReqDto createCheckReqDto, ChecklistEntity checklist, UserEntity user) {
        return TodoCheckEntity.builder()
                .title(createCheckReqDto.getTitle())
                .duedate(createCheckReqDto.getDuedate())
                .isCompleted(createCheckReqDto.isCompleted())
                .checklist(checklist)
                .user(user)
                .build();
    }
}
