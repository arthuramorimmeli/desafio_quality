package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Property create(Property property) {
        if (property.getDistrict().getId() == null) {
            districtRepository.saveAndFlush(property.getDistrict());
        }
        List<Room> rooms = property.getRooms();
        Property save = propertyRepository.save(new Property(property.getId(), property.getName(), property.getDistrict(), new ArrayList<>()));
        rooms.forEach(room -> room.setProperty(save));
        List<Room> rooms1 = roomRepository.saveAll(rooms);
        save.setRooms(rooms1);
        return save;
    }

    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.getById(id);
    }

}
