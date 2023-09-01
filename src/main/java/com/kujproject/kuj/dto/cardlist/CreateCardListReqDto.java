package com.kujproject.kuj.dto.cardlist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCardListReqDto {
    @NotEmpty
    @Size(max = 15, message = "카드리스트 제목은 15자를 초과할 수 없습니다.")
    private String title;
    @NotEmpty
    private int cardlistOrder;
}
