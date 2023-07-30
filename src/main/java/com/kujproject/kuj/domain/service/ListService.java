package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.ListRespDto;
import com.kujproject.kuj.dto.list.UpdateListOrderReqDto;
import com.kujproject.kuj.dto.list.UpdateListTitleReqDto;

import java.util.List;

public interface ListService {
    ListEntity findListByListID(Long listId);
    List<ListEntity> findAllListByBoardId(Long boardId);
    UpdateListOrderReqDto changeListOrder(Long listId, UpdateListOrderReqDto updateListOrderReqDto);
    UpdateListTitleReqDto updateListTitle(Long listId, UpdateListTitleReqDto updateListTitleReqDto);
    ListRespDto createList(CreateListReqDto createListReqDto, Long boardId);
    boolean deleteList(Long listId);
}
