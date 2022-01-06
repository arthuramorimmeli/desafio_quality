package com.mercadolivre.wave4.desafio_quality.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.mercadolivre.wave4.desafio_quality.entities.District;
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

        assertEquals(metersExpected, propertyService.getMetersOfProperty(house));
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

        assertNotEquals(metersExpected, propertyService.getMetersOfProperty(house));
    }

    @Test
    public void IndicatePriceOfPropertyBasedOnTheTotalAreaValueDistrict(){
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

        District district = District.builder()
                .name("Casa Verde")
                .footageValue(7000.0)
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .district(district)
                .build();

        Double valueExpected = ((bathroom.getWidth().doubleValue() * bathroom.getLength().doubleValue())
                + (kit.getLength().doubleValue()* kit.getWidth().doubleValue())) * district.getFootageValue();

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);

        assertEquals(valueExpected,propertyService.getValueOfProperty(house));
    }

    //        RoomRepository mockRoomRepository = Mockito.mock(RoomRepository.class);
    //        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
    //        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);
    //
    //
//            PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository, mockRoomRepository);
//
//            Mockito.when(mockPropertyRepository.save(house)).thenReturn(house);
}
