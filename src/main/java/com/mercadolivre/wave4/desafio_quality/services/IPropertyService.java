package com.mercadolivre.wave4.desafio_quality.services;

import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;

import java.util.List;

public interface IPropertyService {
    Property create(Property property);
    List<Property> getAll();
    Property findById(Long id);
    Double getValueOfProperty(Long id);
    Double getValueAreaRoom(Room room);
    Double getMetersOfProperty(Long id);
    Room getMaxRoom(Property property);
}
