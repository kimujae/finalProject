package com.kujproject.kuj.dto.card;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCardDescDto {
    @Size(max = 3000, message = "3000자를 초과하여 입력할 수 없습니다.")
    String description;
}
