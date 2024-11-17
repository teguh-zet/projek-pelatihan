package com.moslemwear.bismillahproject.models;

// import java.sql.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer payment;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User idUser;

    @ManyToOne
    @JoinColumn(name = "id_item", referencedColumnName = "id")
    private Item idItem;
}
