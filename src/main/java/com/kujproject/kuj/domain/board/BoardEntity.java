package com.kujproject.kuj.domain.board;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kujproject.kuj.domain.board_user.Board_UserEntity;
import com.kujproject.kuj.domain.list.ListEntity;
import com.kujproject.kuj.dto.board.CreateBoardReqDto;
import com.kujproject.kuj.dto.board.UpdateBoardCoverDto;
import com.kujproject.kuj.dto.board.UpdateBoardPubRangeDto;
import com.kujproject.kuj.dto.board.UpdateBoardTitleDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(builderMethodName = "Builder")
public class BoardEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String title;
    private String cover;
    private boolean isPublic;

//     member , list, starred 와 연관관계 선언
    @OneToMany(mappedBy = "board" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<Board_UserEntity> users = new ArrayList<>();


    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    List<ListEntity> lists = new ArrayList<>();

    public void changeTitle(UpdateBoardTitleDto updateBoardTitleDto) {
        this.title = updateBoardTitleDto.getTitle();
    }

    public void changeCover(UpdateBoardCoverDto updateBoardCoverDto) {
        this.cover = updateBoardCoverDto.getCover();
    }

    public void changePublic(UpdateBoardPubRangeDto updateBoardPubRangeDto) {
        this.isPublic = updateBoardPubRangeDto.isPublic();
    }


    public static BoardEntity convertedBy(CreateBoardReqDto createBoardReqDto) {
        return BoardEntity.Builder()
                .title(createBoardReqDto.getTitle())
                .cover(createBoardReqDto.getCover())
                .isPublic(createBoardReqDto.isPublic())
                .build();
    }

}
