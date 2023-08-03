package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import org.springframework.data.repository.Repository;

public interface ChecklistDao extends Repository<ChecklistEntity, String> {
    ChecklistEntity save(ChecklistEntity checklist);
    ChecklistEntity deleteByChecklistId(Long checklistId);
    ChecklistEntity findChecklistEntityByChecklistId(Long checklistId);
}
