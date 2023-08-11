package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.repository.CardDao;
import com.kujproject.kuj.domain.repository.ChecklistDao;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateProgressReqDto;
import com.kujproject.kuj.dto.checklist.UpdateTitleReqDto;
import com.kujproject.kuj.dto.todo_check.CheckRespDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ChecklistServiceImpl implements ChecklistService{

    private final ChecklistDao checklistDao;
    private final CardDao cardDao;

    public ChecklistServiceImpl(ChecklistDao checklistDao, CardDao cardDao) {
        this.checklistDao = checklistDao;
        this.cardDao = cardDao;
    }

    @Override
    public CreateChecklistReqDto createChecklist(CreateChecklistReqDto createChecklistReqDto, Long cardId) {
        ChecklistEntity checklist = new ChecklistEntity();
        Optional<CardEntity> cardEntity = cardDao.findByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();

            checklist.setTitle(createChecklistReqDto.getTitle());
            checklist.setCard(card);

            checklistDao.save(checklist);
            return createChecklistReqDto;
        }
        return null;
    }

    @Override
    public UpdateProgressReqDto updateProgress(UpdateProgressReqDto updateProgressReqDto, Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findChecklistEntityByChecklistId(checklistId);

        if(checklistEntity.isPresent()) {
            ChecklistEntity checklist = checklistEntity.get();
            checklist.setProgress(updateProgressReqDto.getProgress());

            checklistDao.save(checklist);
            return updateProgressReqDto;
        }
        return null;
    }

    @Override
    public UpdateTitleReqDto updateTitle(UpdateTitleReqDto updateTitleReqDto, Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findChecklistEntityByChecklistId(checklistId);

        if(checklistEntity.isPresent()) {
            ChecklistEntity checklist = checklistEntity.get();
            checklist.setTitle(updateTitleReqDto.getTitle());

            checklistDao.save(checklist);
            return updateTitleReqDto;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteChecklistById(Long checklistId) {
        int deletedEntityCount = checklistDao.deleteByChecklistId(checklistId);
        return true;
    }

    @Override
    public ChecklistRespDto findChecklistById(Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findChecklistEntityByChecklistId(checklistId);

        if(checklistEntity.isPresent()) {
            ChecklistEntity checklist = checklistEntity.get();

        }
        return null;
    }

    @Override
    public List<CheckRespDto> findAllCheckByChecklistID(Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findChecklistEntityByChecklistId(checklistId);
        List<CheckRespDto> checkRespDtoList = new ArrayList<>();

        if(checklistEntity.isPresent()) {
            ChecklistEntity checklist = checklistEntity.get();
            List<TodoCheckEntity> todoCheckList = checklist.getCheck();

            for(TodoCheckEntity todoCheckEntity : todoCheckList) {
                CheckRespDto checkRespDto = new CheckRespDto();

                checkRespDto.setTitle(todoCheckEntity.getTitle());
                checkRespDto.setCompleted(todoCheckEntity.isCompleted());
                checkRespDto.setDuedate(todoCheckEntity.getDuedate());

                checkRespDtoList.add(checkRespDto);
            }
            return checkRespDtoList;
        }
        return null;
    }
}
