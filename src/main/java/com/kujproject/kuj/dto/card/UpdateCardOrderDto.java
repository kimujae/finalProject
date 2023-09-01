package com.kujproject.kuj.dto.card;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
public class UpdateCardOrderDto {
    private int cardOrder;
}
