package com.salim.systempub.repository.divitionrepository.divkesejahteraan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkesejahteraan.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long> {
    
}
