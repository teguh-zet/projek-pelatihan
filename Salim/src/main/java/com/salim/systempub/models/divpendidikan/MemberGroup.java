package com.salim.systempub.models.divpendidikan;

import com.salim.systempub.models.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class MemberGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int value;
    @ManyToOne
    @JoinColumn(name = "id_memberpendidikanpelatihan", referencedColumnName = "id")
    private Member idMember;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_grouppendidikanpelatihan", referencedColumnName = "id")
    private Kelompok idGroup;
}
