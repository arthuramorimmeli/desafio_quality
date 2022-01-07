package com.mercadolivre.wave4.desafio_quality.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.services.impl.DistrictService;
import com.mercadolivre.wave4.desafio_quality.services.impl.PropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class PropertyServiceTest {

    @Mock
    private PropertyRepository mockPropertyRepository;

    @Mock
    private DistrictRepository mockDistrictRepository;

    @InjectMocks
    PropertyService propertyService;

    @Test
    void shouldCheckTotalNumberOfPropertyMeters() {
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

        Double metersExpected = (bathroom.getWidth().doubleValue() * bathroom.getLength().doubleValue()) + (kit.getLength().doubleValue() * kit.getWidth().doubleValue());

        Double metersOfProperty = propertyService.getMetersOfProperty(house);
        assertEquals(metersExpected, metersOfProperty);
    }

    @Test
    void shouldCheckTotalNumberOfPropertyMetersNotEquals() {
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

        Double metersExpected = (bathroom.getWidth().doubleValue() * bathroom.getLength().doubleValue()) + (kit.getLength().doubleValue() * 0);

        assertNotEquals(metersExpected, propertyService.getMetersOfProperty(house));
    }

    @Test
    void IndicatePriceOfPropertyBasedOnTheTotalAreaValueDistrict() {
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
                + (kit.getLength().doubleValue() * kit.getWidth().doubleValue())) * district.getFootageValue();

        assertEquals(valueExpected, propertyService.getValueOfProperty(house));
    }

    @Test
    void IndicatePriceOfPropertyBasedOnTheTotalAreaValueDistrictNotEqual() {
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
                + (kit.getLength().doubleValue() * kit.getWidth().doubleValue()));

        assertNotEquals(valueExpected, propertyService.getValueOfProperty(house));
    }

    @Test
    void DeterminateAreaOfEachRoom() {
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

        Double areaBathroom = bathroom.getLength().doubleValue() * bathroom.getWidth().doubleValue();
        Double areaKitchen = kit.getLength().doubleValue() * kit.getWidth().doubleValue();

        assertEquals(areaBathroom, propertyService.getValueAreaRoom(bathroom));
        assertEquals(areaKitchen, propertyService.getValueAreaRoom(kit));

    }

    @Test
    void shouldCheckMaxRoom() {

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

        Room maxRoom = propertyService.getMaxRoom(house);

        assertEquals(kit, maxRoom);


    }

    @Test
    void shouldCheckMaxRoomNotEquals() {

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
        Room maxRoom = propertyService.getMaxRoom(house);

        assertNotEquals(bathroom, maxRoom);

    }

    @Test
    void shouldCheckValueOfProperty() {
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
                .footageValue(12.0)
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .district(district)
                .build();

        Double valueOfProperty = propertyService.getValueOfProperty(house);

        Double propertyArea = house.getRooms().stream().mapToDouble(room -> room.getWidth().doubleValue() * room.getLength().doubleValue()).sum();
        Double propertyValue = propertyArea * house.getDistrict().getFootageValue();

        assertEquals(propertyValue, valueOfProperty);
    }

    @Test
    void shouldCheckValueOfPropertyId() {
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
                .footageValue(12.0)
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .id(1L)
                .name("House happy")
                .rooms(rooms)
                .district(district)
                .build();

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictService = Mockito.mock(DistrictService.class);

        Mockito.when(mockPropertyRepository.findById(house.getId())).thenReturn(java.util.Optional.of(house));

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictService);
        Double valueOfProperty = propertyService.getValueOfProperty(1L);

        Double propertyArea = house.getRooms().stream().mapToDouble(room -> room.getWidth().doubleValue() * room.getLength().doubleValue()).sum();
        Double propertyValue = propertyArea * house.getDistrict().getFootageValue();



        assertEquals(propertyValue, valueOfProperty);
    }
}
