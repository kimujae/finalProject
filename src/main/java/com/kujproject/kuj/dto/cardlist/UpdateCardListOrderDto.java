package com.kujproject.kuj.dto.cardlist;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCardListOrderDto {
    @NotEmpty
    private int cardlistOrder;
}
