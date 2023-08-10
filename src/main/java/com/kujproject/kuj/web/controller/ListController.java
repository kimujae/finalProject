package com.kujproject.kuj.web.controller;

import com.kujproject.kuj.domain.service.ListService;
import com.kujproject.kuj.dto.list.CreateListReqDto;
import com.kujproject.kuj.dto.list.ListRespDto;
import com.kujproject.kuj.dto.list.UpdateListOrderDto;
import com.kujproject.kuj.dto.list.UpdateListTitleDto;
import com.kujproject.kuj.web.common.code.SuccessCode;
import com.kujproject.kuj.web.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ListController {
    private final ListService listService;

    public ListController(ListService listService) {
        this.listService = listService;
    }



    @PostMapping("/board/{id}/list")
    public ResponseEntity<ApiResponse> createList(
            @Valid @RequestBody CreateListReqDto createListReqDto, @PathVariable Long id) {

        ListRespDto listRespDto = listService.createList(id, createListReqDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(listRespDto)
                .successCode(SuccessCode.INSERT_SUCCESS)
                .build(), HttpStatus.CREATED);
    }

    @GetMapping("/board/{id}/list")
    public ResponseEntity<ApiResponse> findAllListByBoardId(@PathVariable Long id) {

        List<ListRespDto> listRespDtoList = listService.findAllListByBoardId(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(listRespDtoList)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<ApiResponse> findListById(@PathVariable Long id) {

        ListRespDto listRespDto = listService.findListByListID(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(listRespDto)
                .successCode(SuccessCode.SELECT_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/list/{id}/order")
    public ResponseEntity<ApiResponse> changeListOrder(
            @PathVariable Long id, @Valid @RequestBody UpdateListOrderDto updateListOrderDto) {

        updateListOrderDto = listService.changeListOrder(id, updateListOrderDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateListOrderDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @PatchMapping("/list/{id}/title")
    public ResponseEntity<ApiResponse> updadteListTitle(
            @PathVariable Long id, @Valid @RequestBody UpdateListTitleDto updateListTitleDto) {

        updateListTitleDto = listService.updateListTitle(id, updateListTitleDto);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(updateListTitleDto)
                .successCode(SuccessCode.UPDATE_SUCCESS)
                .build(), HttpStatus.OK);
    }


    @DeleteMapping("/list/{id}")
    public ResponseEntity<ApiResponse> deleteByListId(@PathVariable Long id) {
        listService.deleteList(id);
        return new ResponseEntity<>(ApiResponse.builder()
                .result(null)
                .successCode(SuccessCode.DELETE_SUCCESS)
                .build(), HttpStatus.NO_CONTENT);
    }
}
