package com.salim.systempub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.Generation;

@Repository
public interface GenerationRepository extends JpaRepository<Generation,Long> {

}
