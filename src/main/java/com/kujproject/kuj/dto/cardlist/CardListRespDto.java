package com.kujproject.kuj.dto.cardlist;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kujproject.kuj.domain.cardlist.CardListEntity;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
public class CardListRespDto implements Serializable {
    private String title;
    private int cardlistOrder;

    @JsonCreator
    public CardListRespDto(
            @JsonProperty("title") String title,
            @JsonProperty("cardlistOrder") int cardlistOrder) {
        this.title = title;
        this.cardlistOrder = cardlistOrder;
    }

    public static CardListRespDto convertedBy(CardListEntity cardlist) {
        return CardListRespDto.builder()
                .title(cardlist.getTitle())
                .cardlistOrder(cardlist.getCardlistOrder())
                .build();
    }
}
