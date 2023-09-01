package com.kujproject.kuj.dto.card;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
public class UpdateCardTitleDto {
    @Size(max = 15, message = "카드 제목은 15자를 초과할 수 없습니다.")
    private String title;
}
