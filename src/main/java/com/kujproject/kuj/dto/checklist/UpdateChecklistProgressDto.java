package com.kujproject.kuj.dto.checklist;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateChecklistProgressDto {
    @NotEmpty
    int progress;
}
