package com.projecthotel.anisaalawiyah.reporitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projecthotel.anisaalawiyah.models.Booking;

public  interface BookingRepository extends JpaRepository<Booking,Integer> {

    
}
