package com.projecthotel.anisaalawiyah.reporitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecthotel.anisaalawiyah.models.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByStatus(String status);
    
   
    
   

}