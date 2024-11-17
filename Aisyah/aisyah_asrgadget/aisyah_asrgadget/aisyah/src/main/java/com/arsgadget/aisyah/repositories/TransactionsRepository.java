package com.arsgadget.aisyah.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arsgadget.aisyah.models.Transactions;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {

}
