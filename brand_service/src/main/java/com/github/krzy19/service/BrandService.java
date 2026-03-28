package com.github.krzy19.service;

import com.github.krzy19.model.Brand;
import com.github.krzy19.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BrandService {
    private final BrandRepository repo;

    public BrandService(BrandRepository repo)
    {
        this.repo = repo;
    }

    public Optional<Brand> findById(UUID id)
    {
        return repo.findById(id);
    }

    public boolean existsById(UUID id)
    {
        return repo.existsById(id);
    }

    public List<Brand>findAll()
    {
        return repo.findAll();
    }

    public Brand save(Brand brand)
    {
        return repo.save(brand);
    }

    public void delete(UUID id)
    {
        repo.deleteById(id);
    }

}
