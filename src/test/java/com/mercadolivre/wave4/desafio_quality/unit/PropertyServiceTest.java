package com.mercadolivre.wave4.desafio_quality.unit;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import com.mercadolivre.wave4.desafio_quality.entities.Property;
import com.mercadolivre.wave4.desafio_quality.entities.Room;
import com.mercadolivre.wave4.desafio_quality.repositories.DistrictRepository;
import com.mercadolivre.wave4.desafio_quality.repositories.PropertyRepository;
import com.mercadolivre.wave4.desafio_quality.services.impl.PropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        Mockito.when(mockPropertyRepository.save(house)).thenReturn(house);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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


        Mockito.when(mockPropertyRepository.save(house)).thenReturn(house);
        Mockito.when(mockDistrictRepository.saveAndFlush(district)).thenReturn(districtCreated);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

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
        DistrictRepository mockDistrictRepository = Mockito.mock(DistrictRepository.class);

        PropertyService propertyService = new PropertyService(mockPropertyRepository, mockDistrictRepository);

        Room maxRoom = propertyService.getMaxRoom(house);

        assertNotEquals(bathroom, maxRoom);

    }
}
