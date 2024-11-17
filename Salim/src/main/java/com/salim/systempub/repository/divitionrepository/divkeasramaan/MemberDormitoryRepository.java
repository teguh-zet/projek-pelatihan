package com.salim.systempub.repository.divitionrepository.divkeasramaan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.divkeasramaan.MemberDormitory;

@Repository
public interface MemberDormitoryRepository extends JpaRepository<MemberDormitory,Long> {
    
}
