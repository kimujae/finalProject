package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.repository.ChecklistDao;
import com.kujproject.kuj.domain.repository.TodoCheckDao;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.todo_check.*;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TodoCheckServiceImpl implements TodoCheckService{
    private final TodoCheckDao todoCheckDao;
    private final ChecklistDao checklistDao;
    private final UserDao userDao;

    public TodoCheckServiceImpl(TodoCheckDao todoCheckDao, ChecklistDao checklistDao, UserDao userDao) {
        this.todoCheckDao = todoCheckDao;
        this.checklistDao = checklistDao;
        this.userDao = userDao;
    }


    @Override
    public CreateCheckReqDto createCheck(CreateCheckReqDto createCheckReqDto, Long checklistId) {
        TodoCheckEntity todoCheck = new TodoCheckEntity();
        Optional<ChecklistEntity> checklistEntity = checklistDao.findByChecklistId(checklistId);

        if(checklistEntity.isPresent()) {
            ChecklistEntity checklist = checklistEntity.get();
            Optional<UserEntity> userEntity = userDao.findByUserId(createCheckReqDto.getUserId());

            if(userEntity.isPresent()) {
                UserEntity user = userEntity.get();

                todoCheck.setTitle(createCheckReqDto.getTitle());
                todoCheck.setChecklist(checklist);
                todoCheck.setDuedate(createCheckReqDto.getDuedate());
                todoCheck.setCompleted(createCheckReqDto.isCompleted());
                todoCheck.setUser(user);

                todoCheckDao.save(todoCheck);
                return createCheckReqDto;
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean deleteCheckById(Long checkId) {
        int deletedEntityCount = todoCheckDao.deleteByCheckId(checkId);
        return true;
    }

    @Override
    public UpdateCompletedReqDto updateCompleted(UpdateCompletedReqDto updateCompletedReqDto, Long checkId) {
        Optional<TodoCheckEntity> checkEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);

        if(checkEntity.isPresent()) {
            TodoCheckEntity check = checkEntity.get();

            check.setCompleted(updateCompletedReqDto.isCompleted());
            todoCheckDao.save(check);
            return updateCompletedReqDto;
        }
        return null;
    }

    @Override
    public UpdateDateReqDto updateDate(UpdateDateReqDto updateDateReqDto, Long checkId) {
        Optional<TodoCheckEntity> checkEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);

        if(checkEntity.isPresent()) {
            TodoCheckEntity check = checkEntity.get();

            check.setDuedate(updateDateReqDto.getDuedate());
            todoCheckDao.save(check);
            return updateDateReqDto;
        }
        return null;
    }

    @Override
    public UpdateTitleReqDto updateTitle(UpdateTitleReqDto updateTitleReqDto, Long checkId) {
        Optional<TodoCheckEntity> checkEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);

        if(checkEntity.isPresent()) {
            TodoCheckEntity check = checkEntity.get();

            check.setTitle(updateTitleReqDto.getTitle());
            todoCheckDao.save(check);
            return updateTitleReqDto;
        }
        return null;
    }

    @Override
    public CheckRespDto findCheckById(Long checkId) {
        Optional<TodoCheckEntity> todoCheckEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);
        TodoCheckEntity todoCheck = todoCheckEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.TODOCHECK_NOT_FOUND));

        return CheckRespDto.convertedBy(todoCheck);
    }
}
