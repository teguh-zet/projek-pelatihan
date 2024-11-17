package com.salim.systempub.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.Member;

@Repository
public interface MemberPaggingRepository extends PagingAndSortingRepository<Member, Long> {
    
}
