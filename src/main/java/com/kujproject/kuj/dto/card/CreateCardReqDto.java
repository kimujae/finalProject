package com.kujproject.kuj.dto.card;

import com.kujproject.kuj.domain.card.CardEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateCardReqDto {
    @NotEmpty
    private String title;

    public static CreateCardReqDto convertedBy(CardEntity cardEntity) {
        return CreateCardReqDto.builder()
                .title(cardEntity.getTitle())
                .build();
    }
}
