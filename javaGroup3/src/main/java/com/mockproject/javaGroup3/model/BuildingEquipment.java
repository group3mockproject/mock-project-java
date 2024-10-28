package com.mockproject.javaGroup3.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Building_Equipment")
public class BuildingEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "delflag")
    private Boolean delFlag;
}
