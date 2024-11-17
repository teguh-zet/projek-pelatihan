package com.moslemwear.bismillahproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moslemwear.bismillahproject.models.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByNameContainingIgnoreCase(String name);
}
