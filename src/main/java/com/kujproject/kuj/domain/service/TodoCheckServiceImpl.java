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
    public CheckRespDto createCheck(CreateCheckReqDto createCheckReqDto, Long checklistId) {
        Optional<ChecklistEntity> checklistEntity = checklistDao.findByChecklistId(checklistId);
        ChecklistEntity checklist = checklistEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.CHECKLIST_NOT_FOUND));


        Optional<UserEntity> userEntity = userDao.findByUserId(createCheckReqDto.getUserId());
        UserEntity user = userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        TodoCheckEntity todoCheck = TodoCheckEntity.convertedBy(createCheckReqDto, checklist, user);
        todoCheckDao.save(todoCheck);

        return CheckRespDto.convertedBy(todoCheck);
    }

    @Override
    @Transactional
    public void deleteCheckById(Long checkId) {
        int deletedCount = todoCheckDao.deleteByCheckId(checkId);
        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.TODOCHECK_NOT_FOUND);
        }
    }

    @Override
    public UpdateCompletedReqDto updateCompleted(UpdateCompletedReqDto updateCompletedReqDto, Long checkId) {
        Optional<TodoCheckEntity> checkEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);
        TodoCheckEntity todoCheck = checkEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.TODOCHECK_NOT_FOUND));


        todoCheck.changeCompleted(updateCompletedReqDto);
        todoCheckDao.save(todoCheck);
        return updateCompletedReqDto;
    }

    @Override
    public UpdateDateReqDto updateDate(UpdateDateReqDto updateDateReqDto, Long checkId) {
        Optional<TodoCheckEntity> checkEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);
        TodoCheckEntity todoCheck = checkEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.TODOCHECK_NOT_FOUND));


        todoCheck.changeDate(updateDateReqDto);
        todoCheckDao.save(todoCheck);
        return updateDateReqDto;
    }

    @Override
    public UpdateTitleReqDto updateTitle(UpdateTitleReqDto updateTitleReqDto, Long checkId) {
        Optional<TodoCheckEntity> checkEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);
        TodoCheckEntity todoCheck = checkEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.TODOCHECK_NOT_FOUND));


        todoCheck.changeTitle(updateTitleReqDto);
        todoCheckDao.save(todoCheck);
        return updateTitleReqDto;
    }

    @Override
    public CheckRespDto findCheckById(Long checkId) {
        Optional<TodoCheckEntity> todoCheckEntity = todoCheckDao.findTodoCheckEntityByCheckId(checkId);
        TodoCheckEntity todoCheck = todoCheckEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.TODOCHECK_NOT_FOUND));

        return CheckRespDto.convertedBy(todoCheck);
    }
}
