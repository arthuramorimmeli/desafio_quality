package com.mercadolivre.wave4.desafio_quality.dtos;

import com.mercadolivre.wave4.desafio_quality.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {

    private Long id;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(max = 30, message = "O comprimento do comodo não pode exceder 30 caracteres.")
    @Pattern(regexp = "^[A-Z].*$", message = "O comodo deve começar com letra maiuscula")
    private String name;

    @NotNull(message = "A largura do comodo não pode estar vazio")
    @DecimalMax(value = "25.0", message = "A largura maxima permitida do comodo é de 25m")
    private BigDecimal width;

    @NotNull(message = "O comprimento do comodo não pode estar vazio")
    @DecimalMax(value = "33.0", message = "O comprimento maximo permitido do comodo é de 33m")
    private BigDecimal length;

    public static Room convert(RoomDTO roomDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roomDTO, Room.class);
    }
    public static RoomDTO convert(Room room) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(room, RoomDTO.class);
    }
}
