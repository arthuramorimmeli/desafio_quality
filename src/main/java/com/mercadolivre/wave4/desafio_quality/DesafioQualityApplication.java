package com.mercadolivre.wave4.desafio_quality;

import com.mercadolivre.wave4.desafio_quality.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioQualityApplication {

    @Autowired
    private static PropertyService propertyService;

    public static void main(String[] args) {
        SpringApplication.run(DesafioQualityApplication.class, args);
    }

}
