package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.board.*;

public interface BoardService {
    BoardRespDto findBoardByBoardId(Long boardId);
    BoardRespDto createNewBoard(CreateBoardReqDto createBoardReqDto);
    boolean deleteBoard(Long boardId);
    UpdateBoardCoverDto updateCover(String cover);
    UpdateBoardPubRangeDto updatePubRange(boolean pubRange);
    UpdateBoardTitleDto updateTitle(String title);

}
