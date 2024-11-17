package com.projecthotel.anisaalawiyah.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer duration;
    private Integer totalPemesanan;

    @ManyToOne
    @JoinColumn(name="id_customer",referencedColumnName = "idCustomer")
    private Customer idCustomer;

    @ManyToOne
    @JoinColumn( name="id_room",referencedColumnName = "idRoom")
    private Room idRoom;

    public Integer getIdBooking() {
        return idBooking;
    }
 
    public void setIdBooking(Integer idBooking) {
        this.idBooking = idBooking;
    }
       public Integer getTotalPemesanan() {
        return totalPemesanan;
    }
    public void setTotalPemesanan(Integer totalPemesanan) {
       this.totalPemesanan = totalPemesanan;
    }
   

    // Getter dan Setter untuk duration
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

// public boolean isDateRangeOverlap(Booking booking) {
    //     return this.checkInDate.isBefore(booking.getCheckOutDate()) && this.checkOutDate.isAfter(booking.getCheckOutDate());
    // }
    
    
}
