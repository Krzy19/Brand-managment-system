package com.github.krzy19.controller;

import com.github.krzy19.dto.*;
import com.github.krzy19.model.Brand;
import com.github.krzy19.service.BrandService;
import com.github.krzy19.service.event.BrandEventSender;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;
    private final BrandEventSender eventSender;

    public BrandController(BrandService brandService,BrandEventSender eventSender)
    {
        this.brandService=brandService;
        this.eventSender=eventSender;
    }

    private BrandReadDTO toRead(Brand brand)
    {
        return BrandReadDTO.builder().id(brand.getId()).brandName(brand.getBrandName()).dateOfEstablishment(brand.getDateOfEstablishment()).country(brand.getCountry()).build();
    }

    @GetMapping
    public List<BrandReadDTO> getAllBrands()
    {
        return brandService.findAll().stream().map(this::toRead).toList();
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandReadDTO> getBrand(@PathVariable UUID brandId)
    {
        return brandService.findById(brandId).
                map(b->ResponseEntity.ok(toRead(b))).
                orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrandReadDTO> createBrand(@RequestBody @Valid BrandCreateDTO dto)
    {
        Brand b = Brand.builder().
                brandName(dto.getBrandName()).
                dateOfEstablishment(dto.getDateOfEstablishment()).
                country(dto.getCountry()).build();
        Brand saved = brandService.save(b);

        try
        {
            eventSender.brandCreate(saved);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest().
                path("/{id}").
                buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(toRead(saved));
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<BrandReadDTO> updateBrand(@PathVariable UUID brandId,@RequestBody @Valid BrandUpdateDTO dto)
    {
        Optional<Brand> b = brandService.findById(brandId);

        if(b.isEmpty())
            return ResponseEntity.notFound().build();

        Brand existing = b.get();
        existing.setBrandName(dto.getBrandName());
        existing.setDateOfEstablishment(dto.getDateOfEstablishment());
        existing.setCountry(dto.getCountry());

        Brand saved = brandService.save(existing);

        return ResponseEntity.ok(toRead(saved));
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<Void> deleteBrand(@PathVariable UUID brandId) {

        Optional<Brand> b = brandService.findById(brandId);

        if(b.isEmpty())
            return ResponseEntity.notFound().build();

        try
        {
            brandService.delete(brandId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        try
        {
            eventSender.brandDeleted(brandId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return ResponseEntity.noContent().build();
    }
}
