package com.example.hotel.Model;

import org.attoparser.dom.Text;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String typeRoom;
    private String capacity;
    private String size;
    private String status;
    private Integer price;
    @Column(length = 64)
    private String image;
    @Column(columnDefinition = "TEXT")
    private String detail;

    public Long getIdRoom() {
        return id;
    }
    public String getImage() {
        return image;
    }
    public String getTypeRoom() {
        return typeRoom;
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
    public String getDetail() {
        return detail;
    }
}

