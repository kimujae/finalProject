package com.kujproject.kuj.validation.meetingTimeCalculator;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.dto.meetingTimeCalculator.User;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UserCheckValidator implements ConstraintValidator<ExistUserCheckValidator, Object> {
    private final BoardDao boardDao;
    private String board;
    private String users;


    public UserCheckValidator(BoardDao boardDao) {
        this.boardDao = boardDao;
    }


    @Override
    public void initialize(ExistUserCheckValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.board = constraintAnnotation.board();
        this.users = constraintAnnotation.users();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        try {
            Object fieldValue1 = BeanUtils.getPropertyDescriptor(value.getClass(), String.valueOf(board)).getReadMethod().invoke(value);
            List<User> fieldValue2 = (List<User>)BeanUtils.getPropertyDescriptor(value.getClass(), users).getReadMethod().invoke(value);

//            Class<?> entityClass = fieldValue1.getClass();
//            // 엔티티 클래스의 메서드 호출
//            Method entityMethod = entityClass.getMethod("getBoardId");
//            Object boardId = entityMethod.invoke(fieldValue1);

            Optional<BoardEntity> boardEntity = boardDao.findByBoardId((Long) fieldValue1);
            BoardEntity board = boardEntity.orElseThrow(()->
                    new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

            Set<BoardAndUserEntity> foundUsers = board.getUsers();


            for(User user : fieldValue2) {
                for(BoardAndUserEntity userEntity : foundUsers) {
                    if(userEntity.getUser().getUserId().equals(user.getUserId())) continue;

                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
