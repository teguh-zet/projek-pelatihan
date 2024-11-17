package com.project.haiportal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.haiportal.models.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer>{

    // Object getReferenceByCode(String password);
    
}
