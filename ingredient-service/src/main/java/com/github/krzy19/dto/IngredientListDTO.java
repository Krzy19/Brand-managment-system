package com.github.krzy19.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientListDTO {
    private UUID id;
    private String flavor;
    private boolean sparkling;

    private String brandName;
}
