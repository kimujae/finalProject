package com.kujproject.kuj.dto.card;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateDateReqDto {
    LocalDate startdate;
    LocalDate duedate;
}
