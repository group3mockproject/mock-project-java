package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Buildings")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Long buildingId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "size")
    private String size;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "num_of_floor")
    private Integer numOfFloor;

    @Column(name = "construction_year")
    private Integer constructionYear;

    @Column(name = "status")
    private String status;

    @Column(name = "delflag")
    private Boolean delFlag;

    @OneToMany(mappedBy = "building")
    private List<Block> blocks;

    @OneToMany(mappedBy = "building")
    private List<MaintenanceHistory> maintenanceHistories;

    @OneToMany(mappedBy = "building")
    private List<CommonArea> commonAreas;

    @OneToMany(mappedBy = "building")
    private List<BuildingEquipment> buildingEquipments;
}