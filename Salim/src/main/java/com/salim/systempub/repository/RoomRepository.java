package com.salim.systempub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.pasim.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
    
}
