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

    private PropertyRepository propertyRepository;

    private DistrictRepository districtRepository;

    private RoomRepository roomRepository;

    public PropertyService(PropertyRepository propertyRepository, DistrictRepository districtRepository, RoomRepository roomRepository) {
        this.propertyRepository = propertyRepository;
        this.districtRepository = districtRepository;
        this.roomRepository = roomRepository;
    }

    public Property create(Property property) {
//        property.setDistrict(districtRepository.saveAndFlush(property.getDistrict()));
//        property.setRooms(roomRepository.saveAll(property.getRooms()));
//        return propertyRepository.save(property);
        property.addRoom();
        return propertyRepository.save(property);
    }

    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    public Property findById(Long id) {
        return propertyRepository.getById(id);
    }

    public Double getMetersOfProperty(Property property) {
        return property.getRooms().stream().mapToDouble(room -> room.getLength().doubleValue() * room.getWidth().doubleValue()).sum();
    }

    public Double getValueOfProperty(Property property){
        return getMetersOfProperty(property) * property.getDistrict().getFootageValue();
    }

//    public CustomerDTO customerToDTO(Customer customer) {
//        return modelMapper.map(customer, CustomerDTO.class);
//    }

//    public Customer customerToDTO(CustomerDTO customer) {
//        return modelMapper.map(customer, Customer.class);
//    }
}
