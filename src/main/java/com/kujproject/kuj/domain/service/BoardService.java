package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.board.*;
import com.kujproject.kuj.dto.user.UserRespDto;

import java.util.List;


public interface BoardService {
    BoardRespDto findBoardByBoardId(Long boardId);
    List<UserRespDto> findMemberByBoardId(Long boardId);
    BoardRespDto createNewBoard(CreateBoardReqDto createBoardReqDto);
    void deleteBoard(Long boardId);
    UpdateBoardCoverDto updateCover(Long boardId, UpdateBoardCoverDto updateBoardCoverDto);
    UpdateBoardPubRangeDto updatePubRange(Long boardId, UpdateBoardPubRangeDto updateBoardPubRangeDto);
    UpdateBoardTitleDto updateTitle(Long boardId, UpdateBoardTitleDto updateBoardTitleDto);

}
