package com.kujproject.kuj.domain.todo_check;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.user.UserEntity;
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
}
