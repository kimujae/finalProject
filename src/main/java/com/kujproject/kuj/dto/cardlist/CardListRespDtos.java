package com.kujproject.kuj.dto.cardlist;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CardListRespDtos {
    private List<CardListRespDto> cardListRespDtos;

    @JsonCreator
    public CardListRespDtos(@JsonProperty("cardListRespDtos") List<CardListRespDto> cardListRespDtos) {
        this.cardListRespDtos = cardListRespDtos;
    }
}
