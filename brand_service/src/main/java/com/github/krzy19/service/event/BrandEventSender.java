package com.github.krzy19.service.event;

import com.github.krzy19.model.Brand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class BrandEventSender {

    private final RestTemplate rest;

    public BrandEventSender(RestTemplate rest)
    {
        this.rest = rest;
    }

    public void brandCreate(Brand brand)
    {
        Map<String, Object> payload = Map.of(
                "id",brand.getId(),
                "name",brand.getBrandName()
        );

        rest.postForLocation("http://localhost:8082/events/brand-created",payload);
    }

    public void brandDeleted(UUID brandId) {
        rest.delete("http://localhost:8082/events/brand-deleted/" + brandId);
    }
}
