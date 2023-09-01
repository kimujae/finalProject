package com.kujproject.kuj.dto.todo_check;

import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@Hidden
public class CheckRespDto {
    private String title;
    private boolean isCompleted;
    private LocalDate duedate;

    public static CheckRespDto convertedBy(TodoCheckEntity todoCheckEntity) {
        return CheckRespDto.builder()
                .title(todoCheckEntity.getTitle())
                .isCompleted(todoCheckEntity.isCompleted())
                .duedate(todoCheckEntity.getDuedate())
                .build();
    }
}
