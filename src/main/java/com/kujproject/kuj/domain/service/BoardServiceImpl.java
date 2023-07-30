package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.board.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{
    private final BoardDao boardDao;

    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Override
    public BoardRespDto findBoardByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        if(boardEntity.isPresent()) {
            BoardRespDto boardRespDto = new BoardRespDto();
            BoardEntity board = boardEntity.get();
            boardRespDto.setBoardTitle(board.getTitle());
            boardRespDto.setPublic(board.isPublic());
            boardRespDto.setCover(board.getCover());

            return boardRespDto;
        }

        return null;
    }

    @Override
    public List<UserEntity> findMemberByBoardId(Long boardId) {
        List<Board_UserEntity> foundUsers;
        List<UserEntity> members = new ArrayList<>();
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);

        if(boardEntity != null) {
            BoardEntity board = boardEntity.get();
            foundUsers = board.getUsers();

            for (Board_UserEntity user : foundUsers) {
                members.add(user.getUser());
            }
            return members;
        }
        return null;
    }


    @Override
    public BoardEntity createNewBoard(CreateBoardReqDto createBoardReqDto) {
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setCover(createBoardReqDto.getCover());
        boardEntity.setPublic(createBoardReqDto.isPublic());
        boardEntity.setTitle(createBoardReqDto.getTitle());

        return boardDao.save(boardEntity);
    }

    @Override
    public boolean deleteBoard(Long boardId) {
        Optional<BoardEntity> board = boardDao.findByBoardId(boardId);

        if(board.isPresent()) {
            boardDao.deleteByBoardId(boardId);
            return true;
        }

        return false;
    }

    @Override
    public UpdateBoardCoverDto updateCover(Long boardId, UpdateBoardCoverDto updateBoardCoverDto) {
        BoardEntity boardEntity = boardDao.findByBoardId(boardId).get();
        boardEntity.setCover(updateBoardCoverDto.getCover());

        boardDao.save(boardEntity);
        return updateBoardCoverDto;
    }

    @Override
    public UpdateBoardPubRangeDto updatePubRange(Long boardId, UpdateBoardPubRangeDto updateBoardPubRangeDto) {
        BoardEntity boardEntity = boardDao.findByBoardId(boardId).get();
        boardEntity.setPublic(updateBoardPubRangeDto.isPublic());

        boardDao.save(boardEntity);
        return updateBoardPubRangeDto;
    }

    @Override
    public UpdateBoardTitleDto updateTitle(Long boardId, UpdateBoardTitleDto updateBoardTitleDto) {
        BoardEntity boardEntity = boardDao.findByBoardId(boardId).get();
        boardEntity.setTitle(updateBoardTitleDto.getTitle());

        boardDao.save(boardEntity);
        return updateBoardTitleDto;
    }
}
