package com.mercadolivre.wave4.desafio_quality.dtos;

import com.mercadolivre.wave4.desafio_quality.entities.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    private Long id;

    @NotNull(message = "O nome da propriedade não pode estar vazio")
    @NotEmpty(message = "O nome da propriedade não pode estar vazio.")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracteres.")
    @Pattern(regexp = "^[A-Z].*$", message = "O nome da propriedade deve começar com letra maiuscula")
    private String name;

    @Valid
    private DistrictDTO district;

    @Valid
    private List<RoomDTO> rooms = new ArrayList<>();

    public static Property convert(PropertyDTO propertyDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(propertyDTO, Property.class);
    }
    public static PropertyDTO convert(Property property) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(property, PropertyDTO.class);
    }
}
