package com.salim.systempub.repository.divitionrepository.divpelatihaninggris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divpelatihaninggris.MemberDivPelatihanInggris;

@Repository
public interface MemberDivPelatihanInggrisRepository extends JpaRepository<MemberDivPelatihanInggris,Long> {
    
}
