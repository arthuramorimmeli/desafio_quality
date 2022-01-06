package com.mercadolivre.wave4.desafio_quality.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessageDTO {
    private Integer statusCode;
    private String message;
}
