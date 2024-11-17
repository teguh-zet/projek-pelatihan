package com.salim.systempub.repository.ppmb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.PPMB;

@Repository
public interface PPMBRepository extends JpaRepository<PPMB,Long> {

}