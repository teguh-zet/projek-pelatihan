package com.salim.systempub.models.divkebersihan;

import com.salim.systempub.models.Member;
import com.salim.systempub.models.pasim.Room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Piket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_memberpiket",referencedColumnName = "id")
    private Member idmember;
    @ManyToOne
    @JoinColumn(name = "id_roompiket",referencedColumnName = "id")
    private Room idroom;
    private String day;
}
