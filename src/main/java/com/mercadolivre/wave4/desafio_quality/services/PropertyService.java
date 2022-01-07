package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.shared.exceptions.PropertyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictService districtService;

    public PropertyService(PropertyRepository propertyRepository, DistrictService districtService) {
        this.propertyRepository = propertyRepository;
        this.districtService = districtService;
    }

    public Property create(Property property) {
        if (property.getDistrict().getId() == null && property.getDistrict().getName() != null || property.getDistrict().getName().isEmpty()) {
            District districtOnDatabase = districtService.findByName(property.getDistrict().getName());
            if (districtOnDatabase != null) {
                property.setDistrict(districtOnDatabase);
            } else {
                property.setDistrict(districtService.createDistrict(property.getDistrict()));
            }
        }
        property.addRoom();
        return propertyRepository.save(property);
    }

    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException("Propriedade não encontrada"));
    }

    public Double getValueOfProperty(Long id) {
        Property property = this.findById(id);
        return getValueOfProperty(property);
    }

    public Double getValueOfProperty(Property property) {
        return getMetersOfProperty(property) * property.getDistrict().getFootageValue();
    }

    public Double getValueAreaRoom(Room room) {
        return room.getLength().doubleValue() * room.getWidth().doubleValue();
    }

    public Double getMetersOfProperty(Long id) {
        Property property = this.findById(id);
        return getMetersOfProperty(property);
    }

    public Double getMetersOfProperty(Property property) {
        return property.getRooms().stream().mapToDouble(room -> room.getLength().doubleValue() * room.getWidth().doubleValue()).sum();
    }

    public Room getMaxRoom(Long id) {
        Property property = this.findById(id);
        return getMaxRoom(property);
    }

    public Room getMaxRoom(Property property) {
        return property.getRooms().stream().max(Comparator.comparingDouble(room ->
                room.getLength().doubleValue() * room.getWidth().doubleValue())).orElse(null);
    }
}
