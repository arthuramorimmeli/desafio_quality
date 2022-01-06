package com.mercadolivre.wave4.desafio_quality.controller;

import com.mercadolivre.wave4.desafio_quality.dtos.DistrictDTO;
import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @PostMapping
    public ResponseEntity<DistrictDTO> create(@Valid @RequestBody DistrictDTO districtDTO) {
        District district = DistrictDTO.convert(districtDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DistrictDTO.convert(districtService.createDistrict(district)));
    }

    @GetMapping
    public ResponseEntity<List<DistrictDTO>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(districtService.getAllDistricts().stream().map(DistrictDTO::convert).collect(Collectors.toList()));
    }
}
