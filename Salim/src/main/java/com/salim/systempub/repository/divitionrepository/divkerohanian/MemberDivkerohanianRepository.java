package com.salim.systempub.repository.divitionrepository.divkerohanian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkerohanian.MemberDivKerohanian;

@Repository
public interface MemberDivkerohanianRepository extends JpaRepository<MemberDivKerohanian,Long> {
    
}
