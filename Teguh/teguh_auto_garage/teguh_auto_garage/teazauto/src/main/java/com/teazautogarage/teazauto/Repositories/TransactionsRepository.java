package com.teazautogarage.teazauto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teazautogarage.teazauto.Model.Transactions;
import com.teazautogarage.teazauto.Model.User;

// import jakarta.transaction.Transaction;

public interface TransactionsRepository extends JpaRepository<Transactions,Long>{
    List<Transactions> findByUser(User user);

}
