package com.mockproject.javaGroup3.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id", nullable = false)
    private int id;

    @Column(name = "resident_id")
    private Integer residentId;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "complaint_type", nullable = false, length = 50)
    private String complaintType;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @ColumnDefault("getdate()")
    @Column(name = "created_at", columnDefinition = "datetime")
    private Date createdAt;

    @Column(name = "resolved_at", columnDefinition = "datetime")
    private Date resolvedAt;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "delflag")
    private Boolean delflag;

}