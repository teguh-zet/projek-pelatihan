package com.project.haiportal.repositories;

import com.project.haiportal.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    
}
