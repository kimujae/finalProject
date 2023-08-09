package com.kujproject.kuj.dto.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateBoardPubRangeDto {
    @NotEmpty
    boolean isPublic;
}
