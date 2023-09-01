package com.kujproject.kuj.dto.todo_check;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden

public class UpdateCompletedReqDto {
    private boolean isCompleted;
}
