package com.github.krzy19.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SimpleBrand
{
    private UUID id;
    private String brandName;
}
