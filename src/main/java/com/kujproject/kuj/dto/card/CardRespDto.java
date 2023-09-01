package com.kujproject.kuj.dto.card;

import com.kujproject.kuj.domain.card.CardEntity;
import com.kujproject.kuj.dto.checklist.ChecklistRespDto;
import com.kujproject.kuj.dto.comment.CommentRespDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@Hidden
public class CardRespDto {
    private String title;
    private String description;
    private String label;
    private String cover;
    private int cardOrder;
    private String uploadFileName;
    private String downloadFilePath;
    private LocalDate startdate;
    private LocalDate duedate;

    public static CardRespDto convertedBy(CardEntity cardEntity, String downloadFilePath) {
        return CardRespDto.builder()
                .title(cardEntity.getTitle())
                .description(cardEntity.getDescription())
                .label(cardEntity.getLabel())
                .cover(cardEntity.getCover())
                .uploadFileName(cardEntity.getUploadFileName())
                .downloadFilePath(downloadFilePath)
                .cardOrder(cardEntity.getCardOrder())
                .startdate(cardEntity.getStartdate())
                .duedate(cardEntity.getDuedate())
                .build();
    }

    public static CardRespDto convertedBy(CardEntity cardEntity) {
        return CardRespDto.builder()
                .title(cardEntity.getTitle())
                .description(cardEntity.getDescription())
                .label(cardEntity.getLabel())
                .cover(cardEntity.getCover())
                .uploadFileName(cardEntity.getUploadFileName())
                .cardOrder(cardEntity.getCardOrder())
                .startdate(cardEntity.getStartdate())
                .duedate(cardEntity.getDuedate())
                .build();
    }
}
