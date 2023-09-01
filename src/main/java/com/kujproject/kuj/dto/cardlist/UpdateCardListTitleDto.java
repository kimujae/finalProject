package com.kujproject.kuj.dto.cardlist;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
public class UpdateCardListTitleDto {
    @NotEmpty
    @Size(max = 15, message = "")
    private String title;
}
