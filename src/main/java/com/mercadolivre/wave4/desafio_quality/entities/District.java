package com.mercadolivre.wave4.desafio_quality.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "O bairro não pode estar vazio")
    @NotEmpty(message = "O bairro não pode estar vazio")
    @Size(max = 45, message = "O comprimento do bairro não pode exceder 45 caracteres.")
    @Column
    private String name;

    @NotNull(message = "O valor do metro quadrado no bairro não pode estar vazio")
    @DecimalMax(value = "13.0", message = "Valor maximo é 13")
    @Column
    private Double footageValue;
}
