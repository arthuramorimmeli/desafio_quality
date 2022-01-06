package com.mercadolivre.wave4.desafio_quality.dtos;

import com.mercadolivre.wave4.desafio_quality.entities.District;

import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {

    private Long id;

    @NotNull(message = "O bairro não pode estar vazio")
    @NotEmpty(message = "O bairro não pode estar vazio")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    private String name;

    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio")
    @DecimalMax(value = "13.0", message = "Valor maximo é 13")
    private Double footageValue;

    public static District convert(DistrictDTO districtDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(districtDTO, District.class);
    }

    public static DistrictDTO convert(District district) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(district, DistrictDTO.class);
    }
}
