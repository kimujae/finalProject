package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.repository.ListDao;
import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.ListRespDto;
import com.kujproject.kuj.dto.list.UpdateListOrderDto;
import com.kujproject.kuj.dto.list.UpdateListTitleDto;
import com.kujproject.kuj.web.common.code.ErrorCode;
import com.kujproject.kuj.web.config.exception.BusinessExceptionHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListServiceImpl implements ListService{
    private final ListDao listDao;
    private final BoardDao boardDao;

    public ListServiceImpl(ListDao listDao, BoardDao boardDao) {
        this.listDao = listDao;
        this.boardDao = boardDao;
    }


    @Override
    public ListRespDto findListByListID(Long listId) {
        Optional<ListEntity> listEntity = listDao.findByListId(listId);
        ListEntity foundList = listEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.LIST_NOT_FOUND));

        return ListRespDto.convertedBy(foundList);
    }


    @Override
    public List<ListRespDto> findAllListByBoardId(Long boardId) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));


        List<ListEntity> listEntityList = listDao.findAllByBoard(board);
        List<ListRespDto> listRespDtoList = new ArrayList<>();
        for(ListEntity list : listEntityList) {
            listRespDtoList.add(ListRespDto.convertedBy(list));
        }
        return listRespDtoList;
    }


    @Override
    public UpdateListOrderDto changeListOrder(Long listId, UpdateListOrderDto updateListOrderDto) {
        Optional<ListEntity> listEntity = listDao.findByListId(listId);
        ListEntity foundList = listEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.LIST_NOT_FOUND));

        foundList.changeListOrder(updateListOrderDto);
        listDao.save(foundList);
        return updateListOrderDto;
    }


    @Override
    public UpdateListTitleDto updateListTitle(Long listId, UpdateListTitleDto updateListTitleDto) {
        Optional<ListEntity> listEntity = listDao.findByListId(listId);
        ListEntity foundList = listEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.LIST_NOT_FOUND));

        foundList.changeTitle(updateListTitleDto);
        listDao.save(foundList);
        return updateListTitleDto;
    }


    @Override
    public ListRespDto createList(Long boardId, CreateListReqDto createListReqDto) {
        Optional<BoardEntity> boardEntity = boardDao.findByBoardId(boardId);
        BoardEntity board = boardEntity.orElseThrow(() ->
                new BusinessExceptionHandler(ErrorCode.BOARD_NOT_FOUND));

        ListEntity listEntity = ListEntity.convertedBy(createListReqDto, board);
        listDao.save(listEntity);

        ListRespDto listRespDto = ListRespDto.convertedBy(listEntity);
        return listRespDto;
    }


    @Override
    public void deleteList(Long listId) {
        int deletedCount = listDao.deleteByListId(listId);

        if(deletedCount == 0) {
            throw new BusinessExceptionHandler(ErrorCode.USER_NOT_FOUND);
        }
    }
}
