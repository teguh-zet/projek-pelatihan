package com.perpustakaan.susiharyati.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // private String nameBorrow;
    // private String titleBook;
    private String dateBorrow;

    @ManyToOne
    @JoinColumn(name = "idMember",referencedColumnName = "id")
    private Member idMember;

    @ManyToOne
    @JoinColumn(name = "idBook", referencedColumnName = "id")
    private Book idBook;

    @Column(name = "amount")
    private Integer amount;
    
}
