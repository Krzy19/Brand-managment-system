package com.github.krzy19.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientReadDTO {
    private UUID id;
    private String flavor;
    private boolean sparkling;

    private UUID brandId;
    private String brandName;
}
