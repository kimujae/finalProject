package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.dto.board.BoardRespDto;
import com.kujproject.kuj.dto.user.*;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserRespDto signUp(SignUpReqDto signUpReqDto) {
        //password 인코딩
        String encodedPassword = passwordEncoder.encode(signUpReqDto.getPassword());
        signUpReqDto.encodingPasswordBy(encodedPassword);
        signUpReqDto.initailizeRole("ROLE_USER");

        // SignUpReqDto -> UserEntity 변환
        UserEntity.UserEntityBuilder userEntityBuilder = UserEntity.builder();
        UserEntity userEntity = userEntityBuilder.builder(signUpReqDto).build();
        //userEntity.setRole("ROLE_USER"); // 빌더생성이므로 더티체킹 못함.

        userDao.save(userEntity);

        //UserEntity -> UserRespDto 변환
        UserRespDto userRespDto = UserRespDto.convertedBy(userEntity).build();
        return userRespDto;
    }


    @Override
    public UserRespDto findUserById(String userId) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);

        if(userEntity.isPresent()) {
           UserEntity user = userEntity.get();
           UserRespDto userRespDto = UserRespDto.convertedBy(user).build();

           return userRespDto;
        }

        throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    public List<BoardRespDto> findUsersBoard(String userId) {

        Optional<UserEntity> userEntity = userDao.findByUserId(userId);

        if(userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            List<BoardRespDto> boards = new ArrayList<>();
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//                throw new AccessDeniedException("{error : 접근 권한이 없습니다.}");
//            }

            for(Board_UserEntity board : user.getBoards()) {
                BoardRespDto boardRespDto = BoardRespDto.convertedBy(board.getBoard()).build();
                boards.add(boardRespDto);
            }
            return boards;
        }

        throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    @Secured("ROLE_ADMIN")
    public List<UserRespDto> findAllUser() {
        List<UserEntity> allUsers = userDao.findAll();
        List<UserRespDto> userRespDtoList = new ArrayList<>();

        for(UserEntity user : allUsers) {
            UserRespDto userRespDto = UserRespDto.convertedBy(user).build();
            userRespDtoList.add(userRespDto);
        }
        return userRespDtoList;
    }

    @Override
    @Transactional
    public UpdateProfileDto updateUserProfile(String userId, UpdateProfileDto updateProfileDto) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);

        if(userEntity.isPresent()) {
            UserEntity user = userEntity.get();

//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//                throw new AccessDeniedException("{error : 접근 권한이 없습니다.}");
//            }

            user.changeProfile(updateProfileDto);
            userDao.save(user);
            return updateProfileDto;
        }
        throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    @Transactional
    public UpdateEmailDto updateEmail(String userId, UpdateEmailDto updateEmailDto) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);

        if(userEntity.isPresent()) {
            UserEntity user = userEntity.get();

//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//                throw new AccessDeniedException("{error : 접근 권한이 없습니다.}");
//            }

            user.changeEmail(updateEmailDto);
            userDao.save(user);
            return updateEmailDto;
        }
        throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    @Transactional
    public void updatePassword(String userId, UpdatePasswordDto updatePasswordDto) {
        Optional<UserEntity> userEntity = userDao.findByUserId(userId);

        if(userEntity.isPresent()) {
            UserEntity user = userEntity.get();

//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if(authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
//                throw new AccessDeniedException("{error : 접근 권한이 없습니다.}");
//            }

            updatePasswordDto.setPassword(passwordEncoder.encode(updatePasswordDto.getPassword()));
            user.changePassword(updatePasswordDto);
            userDao.save(user);
        }
        throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        int deletedCount = userDao.deleteByUserId(userId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
        }
    }


    @Override
    public boolean existByUserId(String userId) {
        boolean isExistByUserId = userDao.existsByUserId(userId);
        return isExistByUserId;
    }

    @Override
    public boolean existByEmail(String email) {
        boolean isExistByEmail = userDao.existsByEmail(email);
        return isExistByEmail;
    }

    @Override
    public boolean existByPhoneNum(String phoneNum) {
        boolean isExistByPhoneNum = userDao.existsByPhoneNum(phoneNum);
        return isExistByPhoneNum;
    }
}
