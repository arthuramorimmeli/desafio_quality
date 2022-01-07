package com.mercadolivre.wave4.desafio_quality.services.impl;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistrictServiceTest {

    @Test
    void createDistrict() {
        District districtToSave = District.builder()
                .name("Casa Verde")
                .footageValue(12.0)
                .build();

        District districtSaved = District.builder()
                .id(1L)
                .name("Casa Verde")
                .footageValue(12.0)
                .build();


        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);
        DistrictService districtService = new DistrictService(mockDistrictRepository);

        Mockito.when(mockDistrictRepository.save(districtToSave)).thenReturn(districtSaved);

        District district = districtService.createDistrict(districtToSave);
        assertEquals(districtSaved, district);

    }

    @Test
    void getAllDistricts() {
        District district1 = District.builder()
                .id(1L)
                .name("Casa Verde")
                .footageValue(12.0)
                .build();
        District district2 = District.builder()
                .id(1L)
                .name("Casa Verde")
                .footageValue(12.0)
                .build();
        District district3 = District.builder()
                .id(1L)
                .name("Casa Verde")
                .footageValue(12.0)
                .build();

        List<District> districts = new ArrayList<>(Arrays.asList(district1, district2, district3));

        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);
        DistrictService districtService = new DistrictService(mockDistrictRepository);

        Mockito.when(mockDistrictRepository.findAll()).thenReturn(districts);

        List<District> allDistricts = districtService.getAllDistricts();

        assertEquals(districts, allDistricts);
    }

    @Test
    void findByName() {

        District district1 = District.builder()
                .id(1L)
                .name("Casa Verde")
                .footageValue(12.0)
                .build();

        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);
        DistrictService districtService = new DistrictService(mockDistrictRepository);

        Mockito.when(mockDistrictRepository.findByName(district1.getName())).thenReturn(district1);

        District casa_verde = districtService.findByName("Casa Verde");
        assertEquals(district1.getName(), casa_verde.getName());
    }
}