package com.github.krzy19.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandUpdateDTO {
    private String brandName;
    private String dateOfEstablishment;
    private String country;
}
