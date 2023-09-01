package com.kujproject.kuj.dto.card;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCardDescDto {
    @Size(max = 3000, message = "3000자를 초과하여 입력할 수 없습니다.")
    private String description;
}
