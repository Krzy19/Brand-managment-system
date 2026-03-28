package com.github.krzy19.controller;

import com.github.krzy19.dto.*;
import com.github.krzy19.model.Ingredient;
import com.github.krzy19.model.SimpleBrand;
import com.github.krzy19.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController
{
    private final IngredientService ingredientService;

    public IngredientController(IngredientService service)
    {
        this.ingredientService=service;
    }

    private IngredientReadDTO toRead(Ingredient ingredient)
    {
        return IngredientReadDTO.builder().
                id(ingredient.getId()).
                flavor(ingredient.getFlavor()).
                sparkling(ingredient.isSparkling()).
                brandId(ingredient.getBrand()!=null ? ingredient.getBrand().getId(): null ).
                brandName(ingredient.getBrand()!=null ? ingredient.getBrand().getBrandName(): null ).
                build();
    }

    @GetMapping
    public List<IngredientReadDTO> getAll()
    {
        return ingredientService.
                findAll().
                stream().
                map(this::toRead).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientReadDTO> getById(@PathVariable UUID id)
    {
        return ingredientService.findById(id).
                map(i->ResponseEntity.ok(toRead(i))).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<IngredientReadDTO> create(@RequestBody IngredientCreateDTO dto)
    {
        if (dto.getBrandId() == null)
            return ResponseEntity.badRequest().body(null);

        SimpleBrand brand = new SimpleBrand(dto.getBrandId(), dto.getBrandName());

        Ingredient ingredient = Ingredient.builder()
                .flavor(dto.getFlavor())
                .sparkling(dto.isSparkling())
                .brand(brand)
                .build();

        Ingredient saved = ingredientService.save(ingredient);
        return ResponseEntity.ok(toRead(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientReadDTO> update(@PathVariable UUID id, @RequestBody IngredientUpdateDTO dto)
    {
        Optional<Ingredient> i = ingredientService.findById(id);

        if(i.isEmpty())
            return ResponseEntity.notFound().build();

        Ingredient existing = i.get();
        existing.setFlavor(dto.getFlavor());
        existing.setSparkling(dto.isSparkling());

        Ingredient saved = ingredientService.save(existing);

        return ResponseEntity.ok(toRead(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id)
    {
        if(!ingredientService.existsById(id))
            return ResponseEntity.notFound().build();

        try
        {
            ingredientService.delete(id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.noContent().build();
    }

}
