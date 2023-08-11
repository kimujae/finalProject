package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistProgressDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistTitleDto;
import com.kujproject.kuj.dto.todo_check.CheckRespDto;

import java.util.List;

public interface ChecklistService {
    ChecklistRespDto createChecklist(CreateChecklistReqDto createChecklistReqDto, Long cardId);
    UpdateChecklistProgressDto updateProgress(UpdateChecklistProgressDto updateChecklistProgressDto, Long checklistId);
    UpdateChecklistTitleDto updateTitle(UpdateChecklistTitleDto updateChecklistTitleDto, Long checklistId);
    void deleteChecklistById(Long checklistId);
    ChecklistRespDto findChecklistById(Long checklistId);
    List<CheckRespDto> findAllCheckByChecklistID(Long checklistId);
}
