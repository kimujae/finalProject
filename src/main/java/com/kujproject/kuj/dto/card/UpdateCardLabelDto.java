package com.kujproject.kuj.dto.card;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateCardLabelDto {
    @Size(max = 7, message = "카드 레이블은 7자를 초과할 수 없습니다.")
    @Pattern(regexp = "^#[0-9]{6}$")
    String label;
}
