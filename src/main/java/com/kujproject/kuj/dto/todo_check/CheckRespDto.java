package com.kujproject.kuj.dto.todo_check;

import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder(builderMethodName = "builder")
public class CheckRespDto {
    String title;
    boolean isCompleted;
    LocalDate duedate;

    public static CheckRespDto convertedBy(TodoCheckEntity todoCheckEntity) {
        return CheckRespDto.builder()
                .title(todoCheckEntity.getTitle())
                .isCompleted(todoCheckEntity.isCompleted())
                .duedate(todoCheckEntity.getDuedate())
                .build();
    }
}
