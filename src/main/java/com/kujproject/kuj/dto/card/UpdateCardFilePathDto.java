package com.kujproject.kuj.dto.card;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateCardFilePathDto {
    private MultipartFile attachment;
}
