package com.kujproject.kuj.dto.checklist;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(builderMethodName = "builder")
public class ChecklistRespDto {
    String title;
    int progress;

    public static ChecklistRespDto convertedBy(ChecklistEntity checklist) {
        return ChecklistRespDto.builder()
                .title(checklist.getTitle())
                .progress(checklist.getProgress())
                .build();
    }
}
