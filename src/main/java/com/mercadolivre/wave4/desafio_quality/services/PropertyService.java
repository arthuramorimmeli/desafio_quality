package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;


    public Property create(Property property) {
        Property save = propertyRepository.save(property);
        return save;
    }

}
