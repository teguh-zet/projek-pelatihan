package com.salim.systempub.repository.divitionrepository.divkebersihan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkebersihan.MemberDivKebersihan;

@Repository
public interface MemberDivKebersihanRepository extends JpaRepository<MemberDivKebersihan,Long> {
    
}
