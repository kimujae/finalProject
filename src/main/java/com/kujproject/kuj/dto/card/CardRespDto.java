package com.kujproject.kuj.dto.card;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CardRespDto {
    String title;
    String description;
    String label;
    String cover;
    int cardOrder;
    String attachmentPath;
    LocalDate startdate;
    LocalDate duedate;
}
