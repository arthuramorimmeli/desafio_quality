package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService {

    DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public District createDistrict(District district) {
        return districtRepository.save(district);
    }

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District findByName(String name) {
        return districtRepository.findByName(name);
    }
}
