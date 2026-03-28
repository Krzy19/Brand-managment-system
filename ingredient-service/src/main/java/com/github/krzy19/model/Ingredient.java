package com.github.krzy19.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient implements Serializable, Comparable<Ingredient> {

    @Id
    @Builder.Default
    private UUID id= UUID.randomUUID();

    @Column(name="flavor",nullable = false)
    private String flavor;

    @Column(name="sparkling",nullable=false)
    private boolean sparkling;

    @Override
    public int compareTo(Ingredient o)
    {
        int result = this.flavor.compareTo(o.flavor);

        if(result!=0)
            return result;

        return Boolean.compare(this.sparkling,o.sparkling);
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o)
            return true;
        if(!(o instanceof Ingredient other))
            return false;
        return Objects.equals(this.id,other.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "brand_id")),
            @AttributeOverride(name = "brandName", column = @Column(name = "brand_name"))
    })
    private SimpleBrand brand;
}
