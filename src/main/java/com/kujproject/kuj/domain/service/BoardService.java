package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import com.kujproject.kuj.dto.board.*;
import com.kujproject.kuj.dto.user.InvitedUserListDto;
import com.kujproject.kuj.dto.user.UserRespDto;

import java.util.List;


public interface BoardService {
    BoardRespDto findBoardByBoardId(Long boardId);
    List<UserRespDto> findMembersByBoardId(Long boardId);
    BoardRespDto createBoard(CreateBoardReqDto createBoardReqDto);
    void deleteBoard(Long boardId);
    boolean inviteMember(Long boardId, InvitedUserListDto invitedUserListDto);
    UpdateBoardCoverDto updateCover(Long boardId, UpdateBoardCoverDto updateBoardCoverDto);
    UpdateBoardPubRangeDto updatePubRange(Long boardId, UpdateBoardPubRangeDto updateBoardPubRangeDto);
    UpdateBoardTitleDto updateTitle(Long boardId, UpdateBoardTitleDto updateBoardTitleDto);

    List<BoardRespDto> findUsersBoard(String userId);
}
