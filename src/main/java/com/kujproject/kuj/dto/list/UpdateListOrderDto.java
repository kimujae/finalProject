package com.kujproject.kuj.dto.list;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdateListOrderDto {
    @NotEmpty
    int listOrder;
}
