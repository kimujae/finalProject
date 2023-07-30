package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.domain.service.ListService;
import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.ListRespDto;
import com.kujproject.kuj.dto.list.UpdateListOrderReqDto;
import com.kujproject.kuj.dto.list.UpdateListTitleReqDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ListController {
    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }



    @PostMapping("/board/{id}/list")
    public ResponseEntity<?> createList(
            @Valid @RequestBody CreateListReqDto createListReqDto, @PathVariable Long id,  BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        listService.createList(createListReqDto, id);
        return ResponseEntity.ok().body(createListReqDto);
    }

    @GetMapping("/board/{id}/list")
    public ResponseEntity<List<ListRespDto>> findAllListByBoardId(@PathVariable Long id) {
        List<ListEntity> lists = listService.findAllListByBoardId(id);
        List<ListRespDto> listsResp = new ArrayList<>();

        for(ListEntity list : lists) {
            ListRespDto l = new ListRespDto();
            l.setListOrder(list.getListOrder());
            l.setTitle(list.getTitle());

            listsResp.add(l);
        }

        return ResponseEntity.ok().body(listsResp);
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<ListRespDto> findListById(@PathVariable Long id) {
        ListRespDto listResp = new ListRespDto();
        ListEntity listEntity = listService.findListByListID(id);

        listResp.setListOrder(listEntity.getListOrder());
        listResp.setTitle(listResp.getTitle());

        return ResponseEntity.ok().body(listResp);
    }


    @PatchMapping("/list/{id}/order")
    public ResponseEntity<?> changeListOrder(
            @PathVariable Long id, @Valid @RequestBody UpdateListOrderReqDto updateListOrderReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        listService.changeListOrder(id, updateListOrderReqDto);
        return ResponseEntity.ok().body(updateListOrderReqDto);
    }


    @PatchMapping("/list/{id}/title")
    public ResponseEntity<?> updadteListTitle(
            @PathVariable Long id, UpdateListTitleReqDto updateListTitleReqDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            return ResponseEntity.badRequest().body(errorMap);
        }

        listService.updateListTitle(id, updateListTitleReqDto);
        return ResponseEntity.ok().body(updateListTitleReqDto);

    }


    @DeleteMapping("/list/{id}")
    public ResponseEntity<Boolean> deleteByListId(@PathVariable Long id) {
        boolean isDeleted = listService.deleteList(id);

        if(isDeleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
