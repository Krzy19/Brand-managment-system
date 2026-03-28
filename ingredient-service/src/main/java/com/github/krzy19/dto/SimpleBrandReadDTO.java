package com.github.krzy19.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
public class SimpleBrandReadDTO {
    private UUID id;
    private String brandName;
}
