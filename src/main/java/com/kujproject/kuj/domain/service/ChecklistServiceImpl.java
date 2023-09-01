package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.repository.CardDao;
import com.kujproject.kuj.domain.repository.ChecklistDao;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistProgressDto;
import com.kujproject.kuj.dto.checklist.UpdateChecklistTitleDto;
import com.kujproject.kuj.dto.todo_check.CheckRespDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService{

    private final ChecklistDao checklistDao;
    private final CardDao cardDao;


    @Override
    public ChecklistRespDto createChecklist(CreateChecklistReqDto createChecklistReqDto, Long cardId) {
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);
        CardEntity card = cardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CARD_NOT_FOUND));

        ChecklistEntity checklist = ChecklistEntity.convertedBy(createChecklistReqDto, card);
        checklistDao.save(checklist);

        return ChecklistRespDto.convertedBy(checklist);
    }

    @Override
    public UpdateChecklistProgressDto updateProgress(UpdateChecklistProgressDto updateChecklistProgressDto, Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findByChecklistId(checklistId);
        ChecklistEntity checklist = checklistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CHECKLIST_NOT_FOUND));


        checklist.changeProgress(updateChecklistProgressDto);
        checklistDao.save(checklist);

        return updateChecklistProgressDto;
    }

    @Override
    public UpdateChecklistTitleDto updateTitle(UpdateChecklistTitleDto updateChecklistTitleDto, Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findByChecklistId(checklistId);
        ChecklistEntity checklist = checklistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CHECKLIST_NOT_FOUND));


        checklist.changeTitle(updateChecklistTitleDto);
        checklistDao.save(checklist);

        return updateChecklistTitleDto;
    }

    @Override
    @Transactional
    public void deleteChecklistById(Long checklistId) {
        int deletedCount = checklistDao.deleteByChecklistId(checklistId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.CHECKLIST_NOT_FOUND);
        }
    }

    @Override
    public ChecklistRespDto findChecklistById(Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findByChecklistId(checklistId);
        ChecklistEntity checklist = checklistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CHECKLIST_NOT_FOUND));

        return ChecklistRespDto.convertedBy(checklist);
    }

    @Override
    public List<CheckRespDto> findAllCheckByChecklistID(Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findByChecklistId(checklistId);
        ChecklistEntity checklist = checklistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CHECKLIST_NOT_FOUND));

        List<TodoCheckEntity> todoCheckList = checklist.getCheck();
        List<CheckRespDto> checkRespDtoList = new ArrayList<>();
        for(TodoCheckEntity todoCheckEntity : todoCheckList) {
            checkRespDtoList.add(CheckRespDto.convertedBy(todoCheckEntity));
        }
        return checkRespDtoList;
    }
}
