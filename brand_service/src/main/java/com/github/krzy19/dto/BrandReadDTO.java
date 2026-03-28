package com.github.krzy19.dto;

import com.github.krzy19.model.Brand;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandReadDTO {
    private UUID id;
    private String brandName;
    private String dateOfEstablishment;
    private String country;

    public static BrandReadDTO fromEntity(Brand brand)
    {
        return BrandReadDTO.builder()
                .id(brand.getId())
                .brandName(brand.getBrandName())
                .dateOfEstablishment(brand.getDateOfEstablishment())
                .country(brand.getCountry())
                .build();
    }
}
