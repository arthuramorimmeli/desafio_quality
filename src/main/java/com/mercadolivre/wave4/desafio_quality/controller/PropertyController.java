package com.mercadolivre.wave4.desafio_quality.controller;


import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<?> createProperty(@Valid @RequestBody Property property) {
        return ResponseEntity.ok(propertyService.create(property));
    }

    @GetMapping
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(propertyService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.findById(id));
    }

}
