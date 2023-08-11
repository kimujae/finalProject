package com.kujproject.kuj.dto.card;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateCardFilePathDto {
    MultipartFile attachment;
}
