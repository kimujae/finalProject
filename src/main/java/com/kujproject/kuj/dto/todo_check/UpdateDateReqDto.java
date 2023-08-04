package com.kujproject.kuj.dto.todo_check;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDateReqDto {
    LocalDate duedate;
}
