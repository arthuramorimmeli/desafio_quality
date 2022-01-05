package com.mercadolivre.wave4.desafio_quality.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O campo não pode estar vazio")
    @NotEmpty(message = "O campo não pode estar vazio")
    @Size(max = 30, message = "O comprimento do comodo não pode exceder 30 caracteres.")
    @Pattern(regexp = "^[A-Z].*$", message = "O comodo deve começar com letra maiuscula")
    @Column
    private String name;

    @NotNull(message = "A largura do comodo não pode estar vazio")
    @DecimalMax(value = "25.0", message = "A largura maxima permitida do comodo é de 25m")
    @Column
    private BigDecimal width;

    @NotNull(message = "O comprimento do comodo não pode estar vazio")
    @DecimalMax(value = "33.0", message = "O comprimento maximo permitido do comodo é de 33m")
    @Column
    private BigDecimal length;

}
