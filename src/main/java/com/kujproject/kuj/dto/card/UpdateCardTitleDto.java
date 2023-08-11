package com.kujproject.kuj.dto.card;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCardTitleDto {
    @Size(max = 15, message = "카드 제목은 15자를 초과할 수 없습니다.")
    String title;
}
