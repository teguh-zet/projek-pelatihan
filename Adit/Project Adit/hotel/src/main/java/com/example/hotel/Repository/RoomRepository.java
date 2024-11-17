package com.example.hotel.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByStatus(String status);
}

