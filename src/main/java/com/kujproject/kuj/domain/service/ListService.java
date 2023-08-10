package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.ListRespDto;
import com.kujproject.kuj.dto.list.UpdateListOrderDto;
import com.kujproject.kuj.dto.list.UpdateListTitleDto;

import java.util.List;

public interface ListService {
    ListRespDto findListByListID(Long listId);
    List<ListRespDto> findAllListByBoardId(Long boardId);
    UpdateListOrderDto changeListOrder(Long listId, UpdateListOrderDto updateListOrderDto);
    UpdateListTitleDto updateListTitle(Long listId, UpdateListTitleDto updateListTitleDto);
    ListRespDto createList(Long boardId, CreateListReqDto createListReqDto);
    void deleteList(Long listId);
}
