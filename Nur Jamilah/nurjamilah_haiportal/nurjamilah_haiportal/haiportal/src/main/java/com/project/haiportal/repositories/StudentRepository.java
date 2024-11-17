package com.project.haiportal.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.haiportal.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}

