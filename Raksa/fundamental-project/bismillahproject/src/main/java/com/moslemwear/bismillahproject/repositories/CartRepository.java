package com.moslemwear.bismillahproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.moslemwear.bismillahproject.models.Cart;
import com.moslemwear.bismillahproject.models.User;
import java.util.List;


public interface CartRepository extends JpaRepository<Cart, Integer>{
    List<Cart> findByIdUser(User idUser);
}
