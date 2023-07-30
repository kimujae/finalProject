package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.user.UserEntity;
import com.kujproject.kuj.dto.board.*;

import java.util.List;


public interface BoardService {
    BoardRespDto findBoardByBoardId(Long boardId);
    List<UserEntity> findMemberByBoardId(Long boardId);
    BoardEntity createNewBoard(CreateBoardReqDto createBoardReqDto);
    boolean deleteBoard(Long boardId);
    UpdateBoardCoverDto updateCover(Long boardId, UpdateBoardCoverDto updateBoardCoverDto);
    UpdateBoardPubRangeDto updatePubRange(Long boardId, UpdateBoardPubRangeDto updateBoardPubRangeDto);
    UpdateBoardTitleDto updateTitle(Long boardId, UpdateBoardTitleDto updateBoardTitleDto);

}
