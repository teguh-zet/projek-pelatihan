package com.salim.systempub.repository.divitionrepository.divpendidikan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divpendidikan.MemberDivPendidikan;

@Repository
public interface MemberDivPendidikanRepository extends JpaRepository<MemberDivPendidikan,Long> {
    
}
