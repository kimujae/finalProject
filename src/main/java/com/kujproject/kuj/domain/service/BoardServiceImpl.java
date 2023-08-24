package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.dto.board.*;
import com.kujproject.kuj.dto.user.UserRespDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardRespDto findBoardByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        return BoardRespDto.convertedBy(board).build();
    }


    @Override
    public List<UserRespDto> findMemberByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(()->
            new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));


        Set<Board_UserEntity> foundUsers = board.getUsers();
        List<UserRespDto> members = new ArrayList<>();
        for (Board_UserEntity user : foundUsers) {
            UserRespDto userRespDto = UserRespDto.convertedBy(user.getUser()).build();
            members.add(userRespDto);
        }
        return members;
    }


    @Override
    public BoardRespDto createNewBoard(CreateBoardReqDto createBoardReqDto) {
        BoardEntity board = BoardEntity.convertedBy(createBoardReqDto);
        boardDao.save(board);

        return BoardRespDto.convertedBy(board).build();
    }

    @Override
    public void deleteBoard(Long boardId) {
        int deletedCount = boardDao.deleteByBoardId(boardId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND);
        }
    }

    @Override
    public UpdateBoardCoverDto updateCover(Long boardId, UpdateBoardCoverDto updateBoardCoverDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board=  boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        board.changeCover(updateBoardCoverDto);
        boardDao.save(board);
        return updateBoardCoverDto;
    }

    @Override
    public UpdateBoardPubRangeDto updatePubRange(Long boardId, UpdateBoardPubRangeDto updateBoardPubRangeDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board=  boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        board.changePublic(updateBoardPubRangeDto);
        boardDao.save(board);
        return updateBoardPubRangeDto;
    }

    @Override
    public UpdateBoardTitleDto updateTitle(Long boardId, UpdateBoardTitleDto updateBoardTitleDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);

        BoardEntity board=  boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        board.changeTitle(updateBoardTitleDto);
        boardDao.save(board);
        return updateBoardTitleDto;
    }
}
