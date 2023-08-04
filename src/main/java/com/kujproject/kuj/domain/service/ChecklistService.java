package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateProgressReqDto;
import com.kujproject.kuj.dto.checklist.UpdateTitleReqDto;

import java.util.List;

public interface ChecklistService {
    CreateChecklistReqDto createChecklist(CreateChecklistReqDto createChecklistReqDto, Long cardId);
    UpdateProgressReqDto updateProgress(UpdateProgressReqDto updateProgressReqDto, Long checklistId);
    UpdateTitleReqDto updateTitle(UpdateTitleReqDto updateTitleReqDto, Long checklistId);
    boolean deleteChecklistById(Long checklistId);
    ChecklistRespDto findChecklistById(Long checklistId);
    List<TodoCheckEntity> findAllCheckByChecklistID(Long checklistId);
}
