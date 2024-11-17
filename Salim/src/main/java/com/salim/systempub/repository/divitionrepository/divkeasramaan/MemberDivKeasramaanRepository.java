package com.salim.systempub.repository.divitionrepository.divkeasramaan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkeasramaan.MemberDivKeasramaan;

@Repository
public interface MemberDivKeasramaanRepository extends JpaRepository<MemberDivKeasramaan,Long> {
    
}
