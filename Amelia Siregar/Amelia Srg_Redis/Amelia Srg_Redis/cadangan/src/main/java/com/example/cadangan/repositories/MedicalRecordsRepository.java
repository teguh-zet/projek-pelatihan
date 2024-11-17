package com.example.cadangan.repositories;

import com.example.cadangan.models.MedicalRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, Integer> {
}