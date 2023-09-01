package com.kujproject.kuj.dto.cardlist;


import com.kujproject.kuj.domain.cardlist.CardListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CardListRespDto {
    private String title;
    private int cardlistOrder;


    public static CardListRespDto convertedBy(CardListEntity cardlist) {
        return CardListRespDto.builder()
                .title(cardlist.getTitle())
                .cardlistOrder(cardlist.getCardlistOrder())
                .build();
    }
}
