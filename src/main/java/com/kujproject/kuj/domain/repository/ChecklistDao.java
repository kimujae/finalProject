package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ChecklistDao extends Repository<ChecklistEntity, String> {
    ChecklistEntity save(ChecklistEntity checklist);
    int deleteByChecklistId(Long checklistId);
    Optional<ChecklistEntity> findByChecklistId(Long checklistId);
}
