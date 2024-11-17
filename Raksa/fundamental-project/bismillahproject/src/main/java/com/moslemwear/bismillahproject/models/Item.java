package com.moslemwear.bismillahproject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer stock;
    private Integer price;
    private String name;
    private String detail;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "id_variety", referencedColumnName = "id")
    private Variety variety;
}
