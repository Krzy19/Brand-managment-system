package com.github.krzy19.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientCreateDTO {
    private String flavor;
    private boolean sparkling;
    private UUID brandId;
    private String brandName;
}
