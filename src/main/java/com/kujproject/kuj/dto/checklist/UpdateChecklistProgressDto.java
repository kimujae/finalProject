package com.kujproject.kuj.dto.checklist;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
public class UpdateChecklistProgressDto {
    @NotEmpty
    private int progress;
}
