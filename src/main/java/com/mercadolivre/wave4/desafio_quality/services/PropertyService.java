package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyService {

    private PropertyRepository propertyRepository;

    private DistrictRepository districtRepository;

    private  final RoomRepository roomRepository;

    public PropertyService(PropertyRepository propertyRepository, DistrictRepository districtRepository, RoomRepository roomRepository) {
        this.propertyRepository = propertyRepository;
        this.districtRepository = districtRepository;
        this.roomRepository = roomRepository;
    }

    public Property create(Property property) {
        if (property.getDistrict().getId() == null) {
        }
        property.addRoom();
        return propertyRepository.save(property);
    }

    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.getById(id);
    }

    public Double calculateMetersOfProperty(Property property) {
        return property.getRooms().stream().mapToDouble(room -> room.getLength().doubleValue() * room.getWidth().doubleValue()).sum();
    }

    public Room getMaxRoom(Property property) {
        return property.getRooms().stream().max(Comparator.comparingDouble(room ->
                room.getLength().doubleValue() * room.getWidth().doubleValue())).orElse(null);
    }
}
