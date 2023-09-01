package com.kujproject.kuj.dto.cardlist;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCardListTitleDto {
    @NotEmpty
    @Size(max = 15, message = "")
    private String title;
}
