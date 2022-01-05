package com.mercadolivre.wave4.desafio_quality.repositories;

import com.mercadolivre.wave4.desafio_quality.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
