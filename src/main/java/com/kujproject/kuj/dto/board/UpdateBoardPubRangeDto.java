package com.kujproject.kuj.dto.board;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter(AccessLevel.PROTECTED)
public class UpdateBoardPubRangeDto {
    @NotEmpty
    private boolean isPublic;
}
