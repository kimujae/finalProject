package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.repository.CardDao;
import com.kujproject.kuj.domain.repository.ChecklistDao;
import com.kujproject.kuj.dto.checklist.CreateChecklistReqDto;
import com.kujproject.kuj.dto.checklist.UpdateProgressReqDto;
import com.kujproject.kuj.dto.checklist.UpdateTitleReqDto;
import org.springframework.stereotype.Service;

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
        Optional<CardEntity> cardEntity = cardDao.findCardEntityByCardId(cardId);

        if(cardEntity.isPresent()) {
            CardEntity card = cardEntity.get();

            checklist.setTitle(createChecklistReqDto.getTitle());
            checklist.setCard(card);

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
    public boolean deleteChecklistById(Long checklistId) {
        ChecklistEntity checklistEntity = checklistDao.deleteByChecklistId(checklistId);
        return true;
    }
}
