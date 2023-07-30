package com.kujproject.kuj.dto.list;

import lombok.Data;

@Data
public class CreateListReqDto {
    String listTitle;
    int listOrder;
    //board_id 넣어줘야하나?
}
