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
public class HistoryBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "idMember",referencedColumnName = "id")
    private Member idMember;

    @ManyToOne
    @JoinColumn(name = "idBook",referencedColumnName = "id")
    private Book idBook;

    @ManyToOne
    @JoinColumn(name = "idBorrow",referencedColumnName = "id")
    private Borrow idBorrow;

    @ManyToOne
    @JoinColumn(name = "idBack",referencedColumnName = "id")
    private Back idBack;
    
}
