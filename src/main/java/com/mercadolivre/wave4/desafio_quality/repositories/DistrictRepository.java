package com.mercadolivre.wave4.desafio_quality.repositories;

import com.mercadolivre.wave4.desafio_quality.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

    District findByName(String name);
}
