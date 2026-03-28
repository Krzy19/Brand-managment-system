package com.github.krzy19.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandListDTO
{
    private UUID id;
    private String brandName;
    private String dateOfEstablishment;
    private String country;
}
