package com.mercadolivre.wave4.desafio_quality.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.RoomRepository;
import com.mercadolivre.wave4.desafio_quality.services.PropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PropertyServiceTest {
    RoomRepository mockRoomRepository = Mockito.mock(RoomRepository.class);
    PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
    DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

    @Test
    public void shouldCheckTotalNumberOfPropertyMeters() {
        Room bathroom = Room.builder()
                .name("Bathroom")
                .length(new BigDecimal(5.0))
                .width(new BigDecimal(5.0))
                .build();
        Room kit = Room.builder()
                .name("kit")
                .length(new BigDecimal(10.0))
                .width(new BigDecimal(15.0))
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .build();

        Double metersExpected = (bathroom.getWidth().doubleValue() * bathroom.getLength().doubleValue()) + (kit.getLength().doubleValue()* kit.getWidth().doubleValue());

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);

        assertEquals(metersExpected, propertyService.calculateMetersOfProperty(house));
    }

    @Test
    public void shouldCheckTotalNumberOfPropertyMetersNotEquals() {
        Room bathroom = Room.builder()
                .name("Bathroom")
                .length(new BigDecimal(5.0))
                .width(new BigDecimal(5.0))
                .build();
        Room kit = Room.builder()
                .name("kit")
                .length(new BigDecimal(10.0))
                .width(new BigDecimal(15.0))
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .build();

        Double metersExpected = (bathroom.getWidth().doubleValue() * bathroom.getLength().doubleValue()) + (kit.getLength().doubleValue()* 0);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);

        assertNotEquals(metersExpected, propertyService.calculateMetersOfProperty(house));
    }

    //        RoomRepository mockRoomRepository = Mockito.mock(RoomRepository.class);
    //        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
    //        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);
    //
    //
//            PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);
//
//            Mockito.when(mockPropertyRepository.save(house)).thenReturn(house);

    @Test
    public void shouldCheckMaxRoom() {

        Room bathroom = Room.builder()
                .name("Bathroom")
                .length(new BigDecimal(5.0))
                .width(new BigDecimal(5.0))
                .build();
        Room kit = Room.builder()
                .name("kit")
                .length(new BigDecimal(10.0))
                .width(new BigDecimal(15.0))
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .build();

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);
        Room maxRoom = propertyService.getMaxRoom(house);

        assertEquals(kit, maxRoom);


    }

    @Test
    public void shouldCheckMaxRoomNotEquals() {

        Room bathroom = Room.builder()
                .name("Bathroom")
                .length(new BigDecimal(5.0))
                .width(new BigDecimal(5.0))
                .build();
        Room kit = Room.builder()
                .name("kit")
                .length(new BigDecimal(10.0))
                .width(new BigDecimal(15.0))
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .build();

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);
        Room maxRoom = propertyService.getMaxRoom(house);

        assertNotEquals(bathroom, maxRoom);

    }
}
