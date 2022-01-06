package com.mercadolivre.wave4.desafio_quality.dtos;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidParamsDTO {
    private Integer statusCode;
    private String message;
    Map<?, ?> arguments;
}
