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
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "dob")
    private java.util.Date dob;

    @Column(name = "position")
    private String position;

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

    @OneToMany(mappedBy = "employee")
    private List<Building> buildings;

    @OneToMany(mappedBy = "employee")
    private List<Block> blocks;

    @OneToMany(mappedBy = "employee")
    private List<MaintenanceRequests> maintenanceRequests;

    @OneToMany(mappedBy = "employee")
    private List<Timekeeping> timekeepings;

    @OneToMany(mappedBy = "employee")
    private List<Income> incomes;

    @OneToMany(mappedBy = "employee")
    private List<Outcome> outcomes;

    @OneToMany(mappedBy = "employee")
    private List<MaintenanceHistory> maintenanceHistories;
}