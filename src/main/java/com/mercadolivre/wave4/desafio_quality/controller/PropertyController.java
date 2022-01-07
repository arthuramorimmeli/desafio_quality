package com.mercadolivre.wave4.desafio_quality.controller;

import com.mercadolivre.wave4.desafio_quality.dtos.PropertyAreaDTO;
import com.mercadolivre.wave4.desafio_quality.dtos.PropertyDTO;
import com.mercadolivre.wave4.desafio_quality.dtos.PropertyValueDTO;
import com.mercadolivre.wave4.desafio_quality.dtos.RoomDTO;
import com.mercadolivre.wave4.desafio_quality.services.impl.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(PropertyDTO.convert(propertyService.create(PropertyDTO.convert(propertyDTO))));
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAll() {
        return ResponseEntity.ok(
                propertyService.getAll().stream().map(PropertyDTO::convert).collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.findById(id));
    }

    @GetMapping(value = "/area/{id}")
    public ResponseEntity<PropertyAreaDTO> getArea(@PathVariable Long id) {
        return ResponseEntity.ok(new PropertyAreaDTO(propertyService.getMetersOfProperty(id)));
    }

    @GetMapping(value = "/value/{id}")
    public ResponseEntity<PropertyValueDTO> getValue(@PathVariable Long id) {
        return ResponseEntity.ok(new PropertyValueDTO(propertyService.getValueOfProperty(id)));
    }

    @GetMapping(value = "/largest/{id}")
    public ResponseEntity<RoomDTO> getLargest(@PathVariable Long id) {
        return ResponseEntity.ok(RoomDTO.convert(propertyService.getMaxRoom(id)));
    }
}
