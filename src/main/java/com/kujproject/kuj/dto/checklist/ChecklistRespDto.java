package com.kujproject.kuj.dto.checklist;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ChecklistRespDto {
    private String title;
    private int progress;

    public static ChecklistRespDto convertedBy(ChecklistEntity checklist) {
        return ChecklistRespDto.builder()
                .title(checklist.getTitle())
                .progress(checklist.getProgress())
                .build();
    }
}
