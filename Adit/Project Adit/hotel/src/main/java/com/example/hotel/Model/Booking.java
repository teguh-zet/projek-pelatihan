package com.example.hotel.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBooking;

    private String name;
    private String phone;
    private String email;
    private String gender;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer duration;
    private Integer totalPemesanan;

    @ManyToOne
    @JoinColumn(name = "id_room", referencedColumnName = "id")
    private Room idRoom;

    @ManyToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    private User user;

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

    public void calculateTotalPemesanan() {
        // Misalnya, Anda memiliki harga per hari di dalam objek room
        int hargaPerHari = this.idRoom.getPrice(); // Gantilah dengan properti yang sesuai di kelas Room

        // Hitung total pembayaran berdasarkan durasi dan harga per hari
        this.totalPemesanan = this.duration * hargaPerHari;
    }

    // Getter dan Setter untuk duration
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
