package com.mockproject.javaGroup3.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_contracts")
public class EmployeeContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @Column(name = "contract_start_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "contract_end_date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "contract_type", nullable = false)
    private String contractType;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;  // Mặc định là chưa bị xóa

    // Thêm các trường mới
    @Column(name = "position", length = 100)
    private String position;

    @Column(name = "benefits", length = 255)
    private String benefits;

    @Column(name = "terms", length = 255)
    private String terms;
}
