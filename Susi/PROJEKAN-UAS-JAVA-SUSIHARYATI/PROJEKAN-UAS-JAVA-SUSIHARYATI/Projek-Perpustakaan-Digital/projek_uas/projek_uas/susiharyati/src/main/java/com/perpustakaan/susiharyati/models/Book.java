package com.perpustakaan.susiharyati.models;

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
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // private Integer image;
    
    private String title;
    private String author;
    // @ManyToOne
    // @JoinColumn(name = "id_publish", referencedColumnName = "id_publ")
    // private Publisher publisher;

    @ManyToOne
    @JoinColumn(name = "id_member", referencedColumnName = "id")
    private Member id_Member;
    

    private String year;
    private String statusBook;
    private Integer amountBook;

    
}
