package com.salim.systempub.repository.secretary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salim.systempub.models.Secretary;

@Repository
public interface SecretaryRepository extends JpaRepository<Secretary,Long> {
    
}
