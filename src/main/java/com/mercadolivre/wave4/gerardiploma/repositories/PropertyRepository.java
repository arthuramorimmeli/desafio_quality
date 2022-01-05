package com.mercadolivre.wave4.gerardiploma.repositories;

import com.mercadolivre.wave4.gerardiploma.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
