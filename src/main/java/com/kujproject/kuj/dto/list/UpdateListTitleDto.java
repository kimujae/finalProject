package com.kujproject.kuj.dto.list;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateListTitleDto {
    @NotEmpty
    @Size(max = 15, message = "")
    String title;
}
