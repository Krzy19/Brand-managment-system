package com.github.krzy19.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="juice_brands")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand implements Serializable, Comparable<Brand>
{

    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "date_of_est", nullable = false)
    private String dateOfEstablishment;

    @Column(name = "country",nullable = false)
    private String country;


    @Override
    public int compareTo(Brand o) {

        int result = this.brandName.compareTo(o.brandName);

        if(result!=0)
            return result;

        result = this.dateOfEstablishment.compareTo(o.dateOfEstablishment);

        if(result!=0)
            return result;

        return this.country.compareTo(o.country);
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o)
            return true;
        if(!(o instanceof Brand other))
            return false;
        return Objects.equals(this.id,other.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }
}
