package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.todo_check.*;

public interface TodoCheckService {
    CheckRespDto createCheck(CreateCheckReqDto createCheckReqDto, Long checklistId);
    void deleteCheckById(Long checkId);
    UpdateCompletedReqDto updateCompleted(UpdateCompletedReqDto updateCompletedReqDto, Long checkId);
    UpdateDateReqDto updateDate(UpdateDateReqDto updateDateReqDto, Long checkId);
    UpdateTitleReqDto updateTitle(UpdateTitleReqDto updateTitleReqDto, Long checkId);
    CheckRespDto findCheckById(Long checkId);
}
