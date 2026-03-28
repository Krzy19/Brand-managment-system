package com.github.krzy19.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientUpdateDTO {
    private String flavor;
    private boolean sparkling;
}
