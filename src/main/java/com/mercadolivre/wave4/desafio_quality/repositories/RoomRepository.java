package com.mercadolivre.wave4.desafio_quality.repositories;

import com.mercadolivre.wave4.desafio_quality.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
