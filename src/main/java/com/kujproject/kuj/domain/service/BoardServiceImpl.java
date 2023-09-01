package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.repository.BoardAndUserDao;
import com.kujproject.kuj.domain.repository.UserDao;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.board.*;
import com.kujproject.kuj.dto.user.InvitedUserListDto;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardDao boardDao;
    private final UserDao userDao;
    private final BoardAndUserDao boardAndUserDao;


    @Override
    public BoardRespDto findBoardByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        return BoardRespDto.convertedBy(board).build();
    }


    @Override
    public List<UserRespDto> findMembersByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(()->
            new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        Set<BoardAndUserEntity> foundUsers = board.getUsers();
        List<UserRespDto> members = new ArrayList<>();
        for (BoardAndUserEntity user : foundUsers) {
            UserRespDto userRespDto = UserRespDto.convertedBy(user.getUser()).build();
            members.add(userRespDto);
        }
        return members;
    }


    @Override
    @Transactional
    public BoardRespDto createBoard(CreateBoardReqDto createBoardReqDto) {
        BoardEntity board = BoardEntity.convertedBy(createBoardReqDto);
        Optional<UserEntity> userEntity = userDao.findByUserId(createBoardReqDto.getUserId());
        UserEntity user = userEntity.orElseThrow(()->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        BoardAndUserEntity boardAndUserEntity = new BoardAndUserEntity();
        boardAndUserEntity.setBoard(board);
        boardAndUserEntity.setUser(user);

        boardDao.save(board);
        boardAndUserDao.save(boardAndUserEntity);
        return BoardRespDto.convertedBy(board).build();
    }


    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        int deletedCount = boardDao.deleteByBoardId(boardId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND);
        }
    }


    @Override
    @Transactional
    public boolean inviteMember(Long boardId, InvitedUserListDto invitedUserListDto) {
        List<String> users = invitedUserListDto.getUsers();
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(()->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        for(String userId : users) {
            BoardAndUserEntity boardAndUserEntity = new BoardAndUserEntity();
            Optional<UserEntity> userEntity = userDao.findByUserId(userId);
            UserEntity user = userEntity.orElseThrow(()->
                    new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

            boardAndUserEntity.setBoard(board);
            boardAndUserEntity.setUser(user);
            boardAndUserDao.save(boardAndUserEntity);
        }

        return true;
    }


    @Override
    @Transactional
    public UpdateBoardCoverDto updateCover(Long boardId, UpdateBoardCoverDto updateBoardCoverDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board=  boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        board.changeCover(updateBoardCoverDto);
        boardDao.save(board);
        return updateBoardCoverDto;
    }


    @Override
    @Transactional
    public UpdateBoardPubRangeDto updatePubRange(Long boardId, UpdateBoardPubRangeDto updateBoardPubRangeDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board=  boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        board.changePublic(updateBoardPubRangeDto);
        boardDao.save(board);
        return updateBoardPubRangeDto;
    }


    @Override
    @Transactional
    public UpdateBoardTitleDto updateTitle(Long boardId, UpdateBoardTitleDto updateBoardTitleDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);

        BoardEntity board=  boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        board.changeTitle(updateBoardTitleDto);
        boardDao.save(board);
        return updateBoardTitleDto;
    }

    @Override
    public List<BoardRespDto> findUsersBoard(String userId) {

        Optional<UserEntity> userEntity = userDao.findByUserId(userId);
        UserEntity user =  userEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND));

        List<BoardRespDto> boards = new ArrayList<>();
        for(BoardAndUserEntity board : user.getBoards()) {
            BoardRespDto boardRespDto = BoardRespDto.convertedBy(board.getBoard()).build();
            boards.add(boardRespDto);
        }
        return boards;
    }
}
