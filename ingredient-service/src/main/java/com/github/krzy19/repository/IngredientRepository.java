package com.github.krzy19.repository;

import com.github.krzy19.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, UUID>
{
    List<Ingredient> findByBrandId(UUID brandId);
}
