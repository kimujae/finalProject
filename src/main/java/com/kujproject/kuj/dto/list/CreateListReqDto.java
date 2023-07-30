package com.kujproject.kuj.dto.list;

import lombok.Data;

@Data
public class CreateListReqDto {
    String title;
    int listOrder;
    //board_id 넣어줘야하나?
}
