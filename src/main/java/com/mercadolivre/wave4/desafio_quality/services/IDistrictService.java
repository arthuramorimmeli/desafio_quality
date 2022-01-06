package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.District;

import java.util.List;

public interface IDistrictService {
    District createDistrict(District district);
    List<District> getAllDistricts();
}
