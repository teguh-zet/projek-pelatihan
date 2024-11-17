package com.perpustakaan.susiharyati.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Back {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idMember",referencedColumnName = "id")
    private Member idMember;

    @ManyToOne
    @JoinColumn(name = "idBook", referencedColumnName = "id")
    private Book idBook;
    
    private Integer amountBack;
    private String dateBack;
}
