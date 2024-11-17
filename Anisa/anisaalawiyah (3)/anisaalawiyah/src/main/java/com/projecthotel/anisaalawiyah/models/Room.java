package com.projecthotel.anisaalawiyah.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoom;
    @Column(length = 64)
    private String image;
    private String type;
    private String size;
    private Integer price;
    private String status;

    public Integer getIdRoom() {
        return idRoom;
    }
    public String getImage() {
        return image;
    }
    public String getType() {
        return type;
    }
    public String getSize() {
        return size;
    }
    public Integer getPrice() {
        return price;
    }
    public String getStatus() {
        return status;
    }

   
}
