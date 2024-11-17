package com.project.haiportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.haiportal.models.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{
    
}
