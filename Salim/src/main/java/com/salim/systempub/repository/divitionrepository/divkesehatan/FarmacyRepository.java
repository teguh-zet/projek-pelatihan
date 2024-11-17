package com.salim.systempub.repository.divitionrepository.divkesehatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkesehatan.Farmacy;

@Repository
public interface FarmacyRepository extends JpaRepository<Farmacy,Long> {
    
}
