package com.kujproject.kuj.domain.repository;

import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface TodoCheckDao extends Repository<TodoCheckEntity, String> {
    TodoCheckEntity save(TodoCheckEntity todoCheck);
    TodoCheckEntity deleteByCheckId(Long todoCheckId);
    Optional<TodoCheckEntity> findTodoCheckEntityByCheckId(Long id);
}
