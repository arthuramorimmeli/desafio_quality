package com.mercadolivre.wave4.gerardiploma.repositories;

import com.mercadolivre.wave4.gerardiploma.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
