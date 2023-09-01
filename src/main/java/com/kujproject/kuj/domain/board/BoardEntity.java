package com.kujproject.kuj.domain.board;

import com.kujproject.kuj.domain.board_user.BoardAndUserEntity;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import com.kujproject.kuj.dto.board.CreateBoardReqDto;
import com.kujproject.kuj.dto.board.UpdateBoardCoverDto;
import com.kujproject.kuj.dto.board.UpdateBoardPubRangeDto;
import com.kujproject.kuj.dto.board.UpdateBoardTitleDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    private String title;
    private String cover;
    private boolean isPublic;

    @OneToMany(mappedBy = "board" , cascade = CascadeType.REMOVE)
    Set<BoardAndUserEntity> users = new HashSet<>();


    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    List<CardListEntity> cardLists = new ArrayList<>();

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
        return BoardEntity.builder()
                .title(createBoardReqDto.getTitle())
                .cover(createBoardReqDto.getCover())
                .isPublic(createBoardReqDto.isPublic())
                .build();
    }

}
