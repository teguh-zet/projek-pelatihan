package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.Booking;
import com.example.hotel.Model.User;

import java.util.List;


public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByUser(User user);

    // Tambahan
    Booking findTopByOrderByIdBookingDesc();
}
