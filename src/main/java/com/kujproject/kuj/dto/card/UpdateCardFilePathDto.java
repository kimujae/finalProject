package com.kujproject.kuj.dto.card;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Hidden
public class UpdateCardFilePathDto {
    private MultipartFile attachment;
}
