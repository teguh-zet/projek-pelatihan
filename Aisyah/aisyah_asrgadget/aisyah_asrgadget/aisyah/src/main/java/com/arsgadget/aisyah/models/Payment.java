package com.arsgadget.aisyah.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_transaction", referencedColumnName = "id")
    private Transactions idTransaction;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "total_payment")
    private Long totalPayment;

    @Column(name = "type_of_payment")
    private String typeOfPayment;

}
