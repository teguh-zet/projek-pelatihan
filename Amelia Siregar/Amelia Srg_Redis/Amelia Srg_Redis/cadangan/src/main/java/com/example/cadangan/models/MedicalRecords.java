package com.example.cadangan.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
public class MedicalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String complaint;
    private String diagnosis;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date check_date;

    @ManyToOne
    @JoinColumn(name = "id_patient", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "id_doctor", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "id_medicine", referencedColumnName = "id")
    private Medicine medicine;

    @ManyToOne
    @JoinColumn(name = "id_polyclinic", referencedColumnName = "id")
    private Polyclinic polyclinic;
}