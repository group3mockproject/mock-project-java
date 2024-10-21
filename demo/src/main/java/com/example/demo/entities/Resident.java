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
@Table(name = "Resident")
public class Resident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resident_id")
    private Long residentId;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartments apartment;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "date_of_birth")
    private java.util.Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "SSN")
    private String ssn;

    @Column(name = "status")
    private String status;

    @Column(name = "password")
    private String password;

    @Column(name = "delflag")
    private Boolean delFlag;

    @OneToMany(mappedBy = "resident")
    private List<MaintenanceRequests> maintenanceRequests;

    @OneToMany(mappedBy = "resident")
    private List<PaymentResident> payments;

    @OneToMany(mappedBy = "resident")
    private List<ResidentUtilityUsage> utilityUsages;
}
