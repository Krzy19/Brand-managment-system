package com.github.krzy19.service;

import com.github.krzy19.model.Ingredient;
import com.github.krzy19.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IngredientService {
    private final IngredientRepository repo;

    public IngredientService(IngredientRepository repo)
    {
        this.repo=repo;
    }

    public Optional<Ingredient> findById(UUID id)
    {
        return repo.findById(id);
    }

    public boolean existsById(UUID id)
    {
        return repo.existsById(id);
    }

    public List<Ingredient> findAll()
    {
        return repo.findAll();
    }

    public List<Ingredient> findByBrandId(UUID brandId)
    {
        return repo.findByBrandId(brandId);
    }

    public Ingredient save(Ingredient ingredient)
    {
        return repo.save(ingredient);
    }

    public void delete(UUID id)
    {
        repo.deleteById(id);
    }
}
