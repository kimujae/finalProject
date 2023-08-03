package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.checklist.ChecklistEntity;
import com.kujproject.kuj.domain.repository.ChecklistDao;
import com.kujproject.kuj.domain.repository.TodoCheckDao;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.todo_check.TodoCheckEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.todo_check.CreateCheckReqDto;
import com.kujproject.kuj.dto.todo_check.UpdateCompletedReqDto;
import com.kujproject.kuj.dto.todo_check.UpdateDateReqDto;
import com.kujproject.kuj.dto.todo_check.UpdateTitleReqDto;
import org.springframework.stereotype.Service;

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
        Optional<ChecklistEntity> checklistEntity = checklistDao.findChecklistEntityByChecklistId(checklistId);

        if(checklistEntity.isPresent()) {
            ChecklistEntity checklist = checklistEntity.get();
            Optional<UserEntity> userEntity = userDao.findById(createCheckReqDto.getUserId());

            if(userEntity.isPresent()) {
                UserEntity user = userEntity.get();

                todoCheck.setTitle(createCheckReqDto.getTitle());
                todoCheck.setChecklist(checklist);
                todoCheck.setDuedate(createCheckReqDto.getDuedate());
                todoCheck.setCompleted(createCheckReqDto.isCompleted());
                todoCheck.setUser(user);
                return createCheckReqDto;
            }
        }
        return null;
    }

    @Override
    public boolean deleteCheckById(Long checkId) {
        todoCheckDao.deleteByCheckId(checkId);
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
}
