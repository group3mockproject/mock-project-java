package com.mockproject.javaGroup3.model;


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
@Table(name = "Utility")
public class Utility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utility_id")
    private Long utilityId;

    @Column(name = "utility_name")
    private String utilityName;

    @Column(name = "unit_cost")
    private Double unitCost;

    @Column(name = "delflag")
    private Boolean delFlag;

    @OneToMany(mappedBy = "utility")
    private List<ResidentUtilityUsage> residentUtilityUsages;

    @OneToMany(mappedBy = "utility")
    private List<UtilityPayment> utilityPayments;
}