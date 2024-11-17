package com.salim.systempub.repository.divitionrepository.divkesehatan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkesehatan.MemberDivKesehatan;

@Repository
public interface MemberDivKesehatanRepository extends JpaRepository<MemberDivKesehatan,Long> {
    
}
