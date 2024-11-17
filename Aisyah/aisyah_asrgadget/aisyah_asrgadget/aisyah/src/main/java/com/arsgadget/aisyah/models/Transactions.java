package com.arsgadget.aisyah.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id")
    private Customer idCustomer;

    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product idProduct;

    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "amount_product")
    private Integer amountProduct;

    @Column(name = "transaction_date")
    private String transactionDate;

}
