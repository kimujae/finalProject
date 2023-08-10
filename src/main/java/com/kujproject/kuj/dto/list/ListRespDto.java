package com.kujproject.kuj.dto.list;


import com.kujproject.kuj.domain.list.ListEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder(builderMethodName = "builder")
public class ListRespDto {
    String title;
    int listOrder;

    public static ListRespDto convertedBy(ListEntity list) {
        return ListRespDto.builder()
                .title(list.getTitle())
                .listOrder(list.getListOrder())
                .build();
    }
}
