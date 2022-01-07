package com.mercadolivre.wave4.desafio_quality.unit;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.services.impl.DistrictService;
import com.mercadolivre.wave4.desafio_quality.services.impl.PropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropertyServiceTest {

    @Test
    void shouldCreateNewPropertyExistsDistrictSuccess() {
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
                .id(1L)
                .name("Casa Verde")
                .footageValue(7000.0)
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .district(district)
                .build();

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

        Mockito.when(mockPropertyRepository.save(house)).thenReturn(house);


        Property property = propertyService.create(house);

        assertEquals(property.getId(), house.getId());
    }

    @Test
    void shouldCreateNewPropertyDontExistsDistrictSuccess() {
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

        District districtCreated = District.builder()
                .id(1L)
                .name("Casa Verde")
                .footageValue(7000.0)
                .build();

        List<Room> rooms = new ArrayList<>(Arrays.asList(bathroom, kit));

        Property house = Property.builder()
                .name("House happy")
                .rooms(rooms)
                .district(district)
                .build();

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

        Mockito.when(mockPropertyRepository.save(house)).thenReturn(house);
        Mockito.when(mockDistrictRepository.saveAndFlush(district)).thenReturn(districtCreated);


        Property property = propertyService.create(house);

        assertTrue(property.getDistrict().getId() != null);
    }

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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

        PropertyRepository mockPropertyRepository = Mockito.mock(PropertyRepository.class);
        DistrictService mockDistrictServie = Mockito.mock(DistrictService.class);
        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictServie);

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
