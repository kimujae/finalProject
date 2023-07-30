package com.kujproject.kuj.domain.service;

import com.kujproject.kuj.domain.board.BoardEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.domain.repository.BoardDao;
import com.kujproject.kuj.domain.repository.ListDao;
import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.ListRespDto;
import com.kujproject.kuj.dto.list.UpdateListOrderReqDto;
import com.kujproject.kuj.dto.list.UpdateListTitleReqDto;
import org.springframework.stereotype.Service;

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
    public ListEntity findListByListID(Long listId) {
        Optional<ListEntity> foundList = listDao.findByListId(listId);

        if(foundList.isPresent()) {
           return foundList.get();
        }
        return null;
    }

    @Override
    public List<ListEntity> findAllListByBoardId(Long boardId) {
        Optional<List<ListEntity>> lists = listDao.findAllByBoardId(boardId);
        if(lists.isPresent()) {
            return lists.get();
        }
        return null;
    }

    @Override
    public UpdateListOrderReqDto changeListOrder(Long listId, UpdateListOrderReqDto updateListOrderReqDto) {
        Optional<ListEntity> foundList = listDao.findByListId(listId);

        if(foundList.isPresent()) {
            ListEntity list = foundList.get();
            list.setListOrder(updateListOrderReqDto.getListOrder());

            return updateListOrderReqDto;
        }
        return null;
    }

    @Override
    public UpdateListTitleReqDto updateListTitle(Long listId, UpdateListTitleReqDto updateListTitleReqDto) {
        Optional<ListEntity> foundList = listDao.findByListId(listId);

        if(foundList.isPresent()) {
            ListEntity list = foundList.get();
            list.setTitle(updateListTitleReqDto.getTitle());

            return updateListTitleReqDto;
        }
        return null;
    }

    @Override
    public ListRespDto createList(CreateListReqDto createListReqDto, Long boardId) {
        ListEntity list = new ListEntity();
        Optional<BoardEntity> board = boardDao.findByBoardId(boardId);


        if(board.isPresent()) {
            list.setTitle(createListReqDto.getListTitle());
            list.setListOrder(createListReqDto.getListOrder());
            list.setBoardEntity(board.get());
            listDao.save(list);

            ListRespDto listResp = new ListRespDto();
            listResp.setListOrder(list.getListOrder());
            listResp.setTitle(list.getTitle());

            return listResp;
        }
        return null;
    }

    @Override
    public boolean deleteList(Long listId) {
        boolean isDeleted = false;
        Optional<ListEntity> list = listDao.deleteByListId(listId);

        if(list.isPresent()) {
            isDeleted = true;
        }
        return isDeleted;
    }
}
